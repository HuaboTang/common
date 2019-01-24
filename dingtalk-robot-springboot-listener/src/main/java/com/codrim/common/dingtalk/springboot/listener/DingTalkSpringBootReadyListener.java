package com.codrim.common.dingtalk.springboot.listener;

import com.google.common.collect.ImmutableMap;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.util.Map;


public class DingTalkSpringBootReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    @SuppressWarnings("Duplicates")
    public void onApplicationEvent(ApplicationReadyEvent event) {
        SimpleCommandLinePropertySource commandLinePropertySource = new SimpleCommandLinePropertySource(event.getArgs());

        if (commandLinePropertySource.containsProperty("dingtalk-webhook")) {
            float secUse = (System.currentTimeMillis() - DingTalkSpringBootStartListener.start) / 1000F;
            final String name = MessageUtils.getServerName(commandLinePropertySource.getProperty("app-name"));
            final String url = commandLinePropertySource.getProperty("dingtalk-webhook");

            //dingtalk-webhook  ding-msg
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
}
