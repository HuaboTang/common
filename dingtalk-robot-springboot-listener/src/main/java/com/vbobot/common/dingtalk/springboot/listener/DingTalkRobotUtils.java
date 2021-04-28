package com.vbobot.common.dingtalk.springboot.listener;

import com.vbobot.common.utils.http.HttpClientUtils;
import com.vbobot.common.utils.json.JsonMapper;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Liang.Zhuge
 * @date 2019/1/24
 */
@Slf4j
public class DingTalkRobotUtils {

    public static void asyncSendMessage(String url, Map<String, Object> message) {
        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(5), new ThreadPoolExecutor.AbortPolicy());

        pool.execute(() -> sendMessage(url, message));
        pool.shutdown();//gracefully shutdown
    }

    public static void sendMessage(String url, Map<String, Object> message) {
        try {
            String returnString = HttpClientUtils.getInst().post(url, JsonMapper.getInstance().toJson(message));
            log.info("Send DingTalk message return:" + returnString);
        } catch (Exception e) {
            log.warn("DingTalk message send failed: {}", e.getMessage());
            log.error(e.getMessage(), e);
        }
    }
}
