package com.codrim.common.utils.json;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liang.Zhuge on 22/03/2017.
 */
public class JsonMapperTest {

    @Test
    public void testFromJson() throws Exception {
        //language=JSON
        String json = "[{\"a\":\"a\", \"b\":\"b\"}]";
        final List<TestObj> list = JsonMapper.getInstance().fromJson(json, List.class, TestObj.class);
        assertEquals(1, list.size());
        assertEquals("a", list.get(0).getA());
    }

    public static class TestObj {
        private String  a;
        private String b;


        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }
}
