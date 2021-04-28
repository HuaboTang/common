package com.vbobot.common.utils.exception;

import org.junit.Test;

/**
 * Unit testEnumSerializer for {@link Assert}
 * Created by ZhugeLiang on 17/01/2017.
 */
public class AssertTest {

    @Test(expected = ServiceException.class)
    public void isTrue() {
        Assert.isTrue(false, 1, "testEnumSerializer");
    }

    @Test(expected = ServiceException.class)
    public void testNotBlank() {
        Assert.notBlank("", 1, "用户尚未绑定手机");
    }

    @Test(expected = ServiceException.class)
    public void testNotBlank2() {
        Assert.notBlank("", 1, "用户尚未绑定手机{}", "123");
    }
}
