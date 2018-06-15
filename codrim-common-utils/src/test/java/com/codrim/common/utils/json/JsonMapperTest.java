package com.codrim.common.utils.json;

import com.fasterxml.jackson.databind.JavaType;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testFromJsonForCollection() throws Exception {
        JsonMapper instance = JsonMapper.getInstance();
        JavaType collectionType = instance.createCollectionType(List.class, TestObj.class);
        String json = "[{\"a\":\"a\", \"b\":\"b\"}]";
        final List<TestObj> list = instance.fromJson(json, collectionType);
        assertEquals(1, list.size());
        assertEquals("a", list.get(0).getA());
    }

    @Test
    public void testForGeneralType() throws Exception {
        String json = "{\"t\":{\"a\":\"a\",\"b\":\"b\"}}";
        JsonMapper instance = JsonMapper.getInstance();
        JavaType type = instance.createJavaType(TestWapper.class, TestObj.class);
        final TestWapper<TestObj> list = instance.fromJson(json, type);
        assertEquals("a", list.t.getA());
    }

    public static class TestWapper<T> {
        private T t;

        public void setT(T t) {
            this.t = t;
        }

        public T getT() {
            return t;
        }
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
