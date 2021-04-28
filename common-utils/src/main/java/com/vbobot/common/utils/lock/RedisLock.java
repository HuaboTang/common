package com.vbobot.common.utils.lock;

import com.vbobot.common.utils.exception.Assert;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import java.io.Closeable;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Liang.Zhuge
 * @date 2018/10/22
 */
@Slf4j
public class RedisLock implements Closeable {
    private final StringRedisTemplate redisTemplate;

    /**
     * 默认请求锁的超时时间(ms 毫秒)
     */
    private static final long TIME_OUT = 100;

    /**
     * 默认锁的有效时间(s)
     */
    public static final int EXPIRE = 60;

    /**
     * 解锁的lua脚本
     */
    public static final String UNLOCK_LUA;

    static {
        UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] "
                + "then "
                + "    return redis.call(\"del\",KEYS[1]) "
                + "else "
                + "    return 0 "
                + "end ";
    }

    /**
     * 锁标志对应的key
     */
    private final String lockKey;

    /**
     * 记录到日志的锁标志对应的key
     */
    @Getter @Setter
    private String lockKeyLog = "";

    /**
     * 锁对应的值
     */
    private String lockValue;

    /**
     * 锁的有效时间(s)
     */
    @Getter @Setter
    private int expireTime = EXPIRE;

    /**
     * 请求锁的超时时间(ms)
     */
    @Getter @Setter
    private long timeOut = TIME_OUT;

    /**
     * 锁标记
     */
    private volatile boolean locked = false;

    final Random random = new Random();

    /**
     * 使用默认的锁过期时间和请求锁的超时时间
     *
     * @param lockKey 锁的key（Redis的Key）
     */
    public RedisLock(StringRedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_lock";
    }

    /**
     * 使用默认的请求锁的超时时间，指定锁的过期时间
     *
     * @param lockKey    锁的key（Redis的Key）
     * @param expireTime 锁的过期时间(单位：秒)
     */
    public RedisLock(StringRedisTemplate redisTemplate, String lockKey, int expireTime) {
        this(redisTemplate, lockKey);
        this.expireTime = expireTime;
    }

    /**
     * 使用默认的锁的过期时间，指定请求锁的超时时间
     *
     * @param lockKey 锁的key（Redis的Key）
     * @param timeOut 请求锁的超时时间(单位：毫秒)
     */
    public RedisLock(StringRedisTemplate redisTemplate, String lockKey, long timeOut) {
        this(redisTemplate, lockKey);
        this.timeOut = timeOut;
    }

    /**
     * 锁的过期时间和请求锁的超时时间都是用指定的值
     *
     * @param lockKey    锁的key（Redis的Key）
     * @param expireTimeInSecond 锁的过期时间(单位：秒)
     * @param timeOut    请求锁的超时时间(单位：毫秒)
     */
    public RedisLock(StringRedisTemplate redisTemplate, String lockKey,
            int expireTimeInSecond, long timeOut) {
        this(redisTemplate, lockKey, expireTimeInSecond);
        this.timeOut = timeOut;
    }

    /**
     * 尝试获取锁 超时返回
     */
    public boolean tryLock() {
        return this.tryLock(timeOut, expireTime);
    }

    public boolean tryLock(long getLockTimeoutMilliscond, int expireTimeSeconds) {
        // 生成随机key
        lockValue = UUID.randomUUID().toString();
        // 请求锁超时时间，纳秒
        long timeout = getLockTimeoutMilliscond * 1000000;
        // 系统当前时间，纳秒
        long nowTime = System.nanoTime();
        while ((System.nanoTime() - nowTime) < timeout) {
            if (this.set(lockKey, lockValue, expireTimeSeconds)) {
                locked = true;
                // 上锁成功结束请求
                return true;
            }

            // 每次请求等待一段时间
            sleep(10, 50000);
        }
        return locked;
    }

    /**
     * 尝试获取锁 立即返回
     *
     * @return 是否成功获得锁
     */
    public boolean lock() {
        lockValue = UUID.randomUUID().toString();
        //不存在则添加 且设置过期时间（单位ms）
        return set(lockKey, lockValue, expireTime);
    }

    /**
     * 以阻塞方式，直到获取锁为止
     *
     * @return 是否成功获得锁
     */
    public boolean lockBlock() {
        lockValue = UUID.randomUUID().toString();
        while (true) {
            //不存在则添加 且设置过期时间（单位ms）
            if (set(lockKey, lockValue, expireTime)) {
                return true;
            }

            // 每次请求等待一段时间
            sleep(10, 50000);
        }
    }

    /**
     * 解锁
     * <p>
     * 可以通过以下修改，让这个锁实现更健壮：
     * <p>
     * 不使用固定的字符串作为键的值，而是设置一个不可猜测（non-guessable）的长随机字符串，作为口令串（token）。 不使用 DEL 命令来释放锁，而是发送一个 Lua
     * 脚本，这个脚本只在客户端传入的值和键的口令串相匹配时，才对键进行删除。 这两个改动可以防止持有过期锁的客户端误删现有锁的情况出现。
     */
    public Boolean unlock() {
        // 只有加锁成功并且锁还有效才去释放锁
        // 只有加锁成功并且锁还有效才去释放锁
        if (locked) {
            return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
                final String str = connection.eval(UNLOCK_LUA.getBytes(), ReturnType.INTEGER, 1, lockKey.getBytes(), lockValue.getBytes()) + "";
                final int iResult = NumberUtils.toInt(str, 0);

                if (iResult == 0 && !StringUtils.isEmpty(lockKeyLog)) {
                    log.info("RedisLock, cluster，unlock {} failed, time: {}", lockKeyLog, System.currentTimeMillis());
                }

                locked = iResult == 0;
                return iResult == 1;
            });
        }

        return true;
    }

    /**
     * 重写redisTemplate的set方法
     * <p>
     * 命令 SET resource-name anystring NX EX max-lock-time 是一种在 Redis 中实现锁的简单方法。
     * <p>
     * 客户端执行以上的命令：
     * <p>
     * 如果服务器返回 OK ，那么这个客户端获得锁。 如果服务器返回 NIL ，那么客户端获取锁失败，可以在稍后再重试。
     *
     * @param key     锁的Key
     * @param value   锁里面的值
     * @param seconds 过去时间（秒）
     */
    private boolean set(final String key, final String value, final long seconds) {
        Assert.isTrue(!StringUtils.isEmpty(key), "key不能为空");

        return redisTemplate.execute((RedisCallback<Optional<Boolean>>) connection -> {
            Boolean result = connection.set(key.getBytes(), value.getBytes(),
                    Expiration.from(seconds, TimeUnit.SECONDS),
                    RedisStringCommands.SetOption.SET_IF_ABSENT);

            final boolean bBool = Optional.ofNullable(result).orElse(false);

            if (!StringUtils.isEmpty(lockKeyLog) && bBool) {
                log.debug("获取锁{}的时间：{}", lockKeyLog, System.currentTimeMillis());
            }

            return Optional.of(bBool);
        }).orElse(false);
    }

    /**
     * @param millis 毫秒
     * @param nanos  纳秒
     */
    private void sleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, random.nextInt(nanos));
        } catch (InterruptedException e) {
            log.info("RedisLock sleep failed, key:{}, value:{}, error:{}", lockKey, lockValue, e.getMessage());
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        this.unlock();
    }
}
