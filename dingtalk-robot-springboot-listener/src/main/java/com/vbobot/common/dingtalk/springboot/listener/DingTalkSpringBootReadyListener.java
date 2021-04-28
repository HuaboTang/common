package com.vbobot.common.dingtalk.springboot.listener;

import com.google.common.collect.ImmutableMap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;


public class DingTalkSpringBootReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if ("1".equals(PropertiesGetter.getProperty("ignore-ready"))) {
            return;
        }

        final String url = PropertiesGetter.getWebhook();

        final String name = PropertiesGetter.getAppName(event.getApplicationContext());
        float secUse = (System.currentTimeMillis() - DingTalkSpringBootStartListener.start) / 1000F;

        Map<String, Object> map = ImmutableMap.of(
                "msgtype", "text",
                "text", ImmutableMap.of("content", "【[OK]】" + name + " start success，cost" + secUse + " seconds.")
        );

        DingTalkRobotUtils.asyncSendMessage(url, map);

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                String timeStatistics = MessageUtils.getTimeStatistics((System.currentTimeMillis() - DingTalkSpringBootStartListener.start) / 1000);
                super.run();
                Map<String, Object> mapStop = ImmutableMap.of(
                        "msgtype", "text",
                        "text", ImmutableMap.of("content", "【[再见]】" + name + " stopping。running" + timeStatistics)
                );

                DingTalkRobotUtils.sendMessage(url, mapStop);
            }
        });
    }
}
