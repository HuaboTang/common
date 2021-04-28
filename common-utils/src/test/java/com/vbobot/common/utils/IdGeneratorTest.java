package com.vbobot.common.utils;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by ZhugeLiang on 31/12/2016.
 */
public class IdGeneratorTest {

    @Test
    public void testGetCurrentIP() throws Exception {
        final String currentIP = IdGenerator.getCurrentIP();
        System.out.println(currentIP);
        assertNotNull(currentIP);
    }
}
