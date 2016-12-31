package com.codrim.common.utils;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>ID生成器</p>
 * <p>生成唯一的ID,分布式环境下可用</p>
 */
public class IdGenerater {
    private volatile static int serialNo = 0;
    private static final String FORMATSTRING = "yyMMddHHmmssSSS";

    /**
     * 使用公平锁防止饥饿
     */
    private static final Lock lock = new ReentrantLock(true);

    private static final int TIMEOUT_SECONDS = 3;

    /**
     * 生成ID，生成规则 时间戳+机器IP最后两位+2位随机数+两位自增序列 <br>
     * 采用可重入锁减小锁持有的粒度，提高系统在高并发情况下的性能
     * 
     * @return 唯一ID
     */
	public static String generateOrder() {
        return getDateTime(FORMATSTRING) + getLastNumOfIP() +
                getRandomNum() + getIncrement();
	}

    /**
     * 获取系统当前时间
     * @param formatStr 格式化字符串
     * @return 当前时间转化后的字符串
     */
    private static String getDateTime(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }

    /**
     * 获取自增序列
     * @return 自增序列
     */
    private static String getIncrement() {
        int tempSerialNo = 0;
        try {
            if (lock.tryLock(TIMEOUT_SECONDS, TimeUnit.SECONDS)) {
                if (serialNo >= 99) {
                    serialNo = 0;
                } else {
                    serialNo = serialNo + 1;
                }
                tempSerialNo = serialNo;
            } else {
                // 指定时间内没有获取到锁，存在激烈的锁竞争或者性能问题，直接报错
                throw new RuntimeException("generateOrder can not get lock!");
            }
        } catch (Exception e) {
            throw new RuntimeException("tryLock throws Exception!");
        } finally {
            lock.unlock();
        }
        if (tempSerialNo < 10) {
            return "0" + tempSerialNo;
        } else {
            return "" + tempSerialNo;
        }
    }

    /**
     * 返回两位随机整数
     * @return 两位随机整数
     */
	private static String getRandomNum() {
		int num = new Random(System.nanoTime()).nextInt(100);
		if (num < 10) {
			return "0" + num;
		} else {
			return num + "";
		}
	}

    /**
     * 获取IP的最后两位数字
     * @return ip的最后两位数字
     */
	private static String getLastNumOfIP() {
		final String ip = StringUtils.replace(getCurrentIP(), ".", "");
		return ip.substring(ip.length() - 2);
	}

    /**
     * 获取本机IP
     * @return 本机IP
     */
	private static String getCurrentIP() {
        String ip = "";
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                final NetworkInterface networkInterface = networkInterfaces.nextElement();
                final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    String s = inetAddresses.toString();
                    s = s.replace("/", "");
                    if (s.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                        return s;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isBlank(ip)) {
            throw new RuntimeException("ip is blank!");
        }
        return ip;
    }
}
