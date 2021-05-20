package com.vbobot.common.utils.exception;

/**
 * @author Bobo
 * @date 2021/4/28
 */
public final class ExceptionUtils {
    private static final String ERROR_STACK_FORMAT = "%s.%s(%s[%s])";

    public static String position(Throwable e) {
        if (e == null) {
            return null;
        }
        final StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            final StackTraceElement firstTrace = stackTrace[0];
            return String.format(ERROR_STACK_FORMAT, firstTrace.getClassName(),
                    firstTrace.getMethodName(),
                    firstTrace.getFileName(),
                    firstTrace.getLineNumber());
        }
        return null;
    }
}
