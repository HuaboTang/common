package com.codrim.common.utils.http;

import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tanghuabo on 01/12/2016.
 */
public class HttpClientUtilsTest {

    @Test
    public void testHttpGet() throws Exception {
        final CodrimHttpResponse res = HttpClientUtils.getInst().httpGet("http://www.baidu.com");
        assertEquals(HttpStatus.SC_OK, res.getStatus());
        System.out.println(res.getResponse());
    }
}
