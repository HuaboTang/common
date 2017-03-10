package com.codrim.common.utils.bean;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Unit test for {@link BeanUtils}
 * Created by ZhugeLiang on 10/03/2017.
 */
public class BeanUtilsTest {

    @Test
    public void populate() throws Exception {
        final Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", 10);
        map.put("c", 10);
        map.put("d", 10);
        final PopulateTest populate = BeanUtils.populate(map, PopulateTest.class);
        assertEquals("a", populate.getA());
        assertEquals((Integer) 10, populate.getB());
        assertNull(populate.getC());
        assertEquals(10, populate.getD());
    }

    public static class PopulateTest {
        private String a;
        private Integer b;
        private String c;
        private int d;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }
    }

}
