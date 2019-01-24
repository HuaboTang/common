package com.codrim.common.dingtalk.springboot.listener;

import lombok.experimental.UtilityClass;

/**
 * @author Liang.Zhuge
 * @date 2019/1/24
 */
@UtilityClass
public final class PropertiesGetter {
    public static String getProperty(String name) {
        return System.getenv(name);
    }
}
