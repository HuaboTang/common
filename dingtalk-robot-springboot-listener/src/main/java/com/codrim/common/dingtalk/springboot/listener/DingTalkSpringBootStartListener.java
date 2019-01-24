package com.codrim.common.dingtalk.springboot.listener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import static com.google.common.collect.ImmutableMap.of;

@Slf4j
public class DingTalkSpringBootStartListener implements ApplicationListener<ApplicationStartingEvent> {

    public static long start = System.currentTimeMillis();

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {

        final String url = PropertiesGetter.getProperty("dingtalk-webhook");
        if (StringUtils.isBlank(url)) {
            log.info("DingTalk-robot-springboot-listener, not config --dingtalk-webhook and --app-name, no send dingTalk message");
            return;
        }

        final String name = MessageUtils.getServerName(PropertiesGetter.getProperty("app-name"));

        Map<String, Object> map = of(
                "msgtype", "text",
                "text", of("content", "【[出差]】" + name + " starting. \n now：" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        );


        DingTalkRobotUtils.asyncSendMessage(url, map);

    }
}
