package com.codrim.common.dingtalk.springboot.listener;

import com.google.common.collect.ImmutableMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;


public class DingTalkSpringBootFailedListener implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {

        final String webhook = PropertiesGetter.getProperty("dingtalk-webhook");

        if (StringUtils.isNotBlank(webhook)) {
            final String name = MessageUtils.getServerName( PropertiesGetter.getProperty("app-name"));

            Map<String, Object> map = ImmutableMap.of(
                    "msgtype", "text",
                    "text", ImmutableMap.of("content", "【[对不起]】" + name + " start failed.")
            );

            DingTalkRobotUtils.asyncSendMessage(webhook, map);
        }
    }
}
