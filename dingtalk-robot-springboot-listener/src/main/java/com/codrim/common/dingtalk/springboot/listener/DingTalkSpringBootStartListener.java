package com.codrim.common.dingtalk.springboot.listener;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.SimpleCommandLinePropertySource;

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

        SimpleCommandLinePropertySource commandLinePropertySource = new SimpleCommandLinePropertySource(event.getArgs());

        if (commandLinePropertySource.containsProperty("dingtalk-webhook")) {

            final String name = MessageUtils.getServerName(commandLinePropertySource.getProperty("app-name"));

            //dingtalk-webhook  ding-msg
            Map<String, Object> map = of(
                    "msgtype", "text",
                    "text", of("content", "【[出差]】" + name + " starting. \n now：" +
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            );

            String url = commandLinePropertySource.getProperty("dingtalk-webhook");

            DingTalkRobotUtils.asyncSendMessage(url, map);
        } else {
            log.info("DingTalk-robot-springboot-listener, not config --dingtalk-webhook and --app-name, no send dingTalk message");
        }
    }
}
