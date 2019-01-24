package com.codrim.common.dingtalk.springboot.listener;

import org.springframework.util.StringUtils;

public class MessageUtils {

    static String getServerName(String argName) {
        if (StringUtils.isEmpty(argName)) {
            return "Server";
        } else {
            return argName + " ";
        }
    }

    static String getTimeStatistics(long seconds) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean beforeAlreadyShow = false;
        long d = seconds / 86400;
        if (d > 0) {
            stringBuilder.append(d).append(" Days");
            beforeAlreadyShow = true;
        }

        long h = (seconds - d * 86400) / 3600;
        if (beforeAlreadyShow) {
            stringBuilder.append(h).append(" hours");
        } else {
            if (h > 0) {
                stringBuilder.append(h).append(" hours");
                beforeAlreadyShow = true;
            }
        }

        long m = (seconds - d * 86400 - h * 3600) / 60;
        if (beforeAlreadyShow) {
            stringBuilder.append(m).append(" minutes");
        } else {
            if (m > 0) {
                stringBuilder.append(m).append(" minutes");
                beforeAlreadyShow = true;
            }
        }
        long s = (seconds - d * 86400 - h * 3600 - m * 60);
        if (s > 0) {
            stringBuilder.append(s).append(" seconds");
        }
        return stringBuilder.toString();
    }
}
