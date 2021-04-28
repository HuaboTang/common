package com.vbobot.common.dingtalk.springboot.listener;

import com.google.common.collect.ImmutableMap;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

import java.util.Map;


public class DingTalkSpringBootFailedListener implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        if ("1".equals(PropertiesGetter.getProperty("ignore-failed"))) {
            return;
        }

        Map<String, Object> map = ImmutableMap.of(
                "msgtype", "text",
                "text", ImmutableMap.of("content", "【[对不起]】"
                        + PropertiesGetter.getAppName(event.getApplicationContext()) + " start failed.")
        );

        DingTalkRobotUtils.asyncSendMessage(PropertiesGetter.getWebhook(), map);
    }
}
