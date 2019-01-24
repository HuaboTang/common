package com.codrim.common.dingtalk.springboot.listener;

import com.google.common.collect.ImmutableMap;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.util.Map;


public class DingTalkSpringBootFailedListener implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {

        SimpleCommandLinePropertySource commandLinePropertySource = new SimpleCommandLinePropertySource(event.getArgs());

        if (commandLinePropertySource.containsProperty("dingtalk-webhook")) {
            final String name = MessageUtils.getServerName(commandLinePropertySource.getProperty("app-name"));

            Map<String, Object> map = ImmutableMap.of(
                    "msgtype", "text",
                    "text", ImmutableMap.of("content", "【[对不起]】" + name + " start failed.")
            );

            String url = commandLinePropertySource.getProperty("dingtalk-webhook");

            DingTalkRobotUtils.asyncSendMessage(url, map);
        }
    }
}
