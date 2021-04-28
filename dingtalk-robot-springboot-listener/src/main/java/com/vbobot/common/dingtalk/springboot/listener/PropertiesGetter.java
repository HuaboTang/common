package com.vbobot.common.dingtalk.springboot.listener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.experimental.UtilityClass;

/**
 * @author Liang.Zhuge
 * @date 2019/1/24
 */
@UtilityClass
public final class PropertiesGetter {
    private static final String WEBHOOK = "dingtalk-webhook";
    private static final String DEFAULT_WEB_HOOK = "https://oapi.dingtalk.com/robot/send?access_token=6a985e5f49a802cac2642824893b5fb20488c107de12b5ee6b98d7582c5d2c4e";

    public static String getProperty(String name) {
        return System.getProperty(name);
    }

    public static String getWebhook() {
        final String pWebhook = getProperty(WEBHOOK);
        return StringUtils.isBlank(pWebhook) ? DEFAULT_WEB_HOOK : pWebhook;
    }

    public static String getAppName(ApplicationContext event) {
        final String appName = PropertiesGetter.getProperty("app-name");
        final String finalAppName = StringUtils.isBlank(appName)
                                    ? event.getEnvironment().getProperty("spring.application.name") : appName;
        final String hostname = getHostname();
        return StringUtils.isNotBlank(finalAppName)
               ? finalAppName + (StringUtils.isBlank(hostname) ? "" : " on " + hostname)
               : "";
    }

    private static String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ignore) {
        }
        return "";
    }
}
