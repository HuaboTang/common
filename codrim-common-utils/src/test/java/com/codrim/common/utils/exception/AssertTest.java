package com.codrim.common.utils.exception;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for {@link Assert}
 * Created by ZhugeLiang on 17/01/2017.
 */
public class AssertTest {

    @Test(expected = ServiceException.class)
    public void isTrue() throws Exception {
        Assert.isTrue(false, 1, "test");
    }

}
