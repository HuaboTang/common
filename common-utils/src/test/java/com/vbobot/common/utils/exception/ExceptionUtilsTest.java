package com.vbobot.common.utils.exception;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;

/**
 * @author Bobo
 * @date 2021/4/28
 */
@Slf4j
public class ExceptionUtilsTest {

    @Test
    public void testPosition() throws Exception {
        try {
            throwException();
        } catch (Exception e) {
            assertEquals("com.vbobot.common.utils.exception.ExceptionUtilsTest.throwException(ExceptionUtilsTest.java[27])", ExceptionUtils.position(e));
            log.error("{}, throws exception: {}", 123, e.getMessage(), e);
        }
    }

    private void throwException() {
        throw new NullPointerException("Error");
    }
}
