package com.vbobot.common.utils.exception;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Unit testEnumSerializer for {@link ServiceException}
 * Created by ZhugeLiang on 17/01/2017.
 */
public class ServiceExceptionTest {

    @Test
    public void testForConstructor() throws Exception {
        final ServiceException serviceException = ServiceException.class.getConstructor(Integer.class, String.class).newInstance(1, "");
        assertNotNull(serviceException);
    }
}
