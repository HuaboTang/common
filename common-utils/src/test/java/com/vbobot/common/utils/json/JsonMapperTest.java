package com.vbobot.common.utils.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import org.junit.Test;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

import static org.junit.Assert.assertEquals;

/**
 * Created by Liang.Zhuge on 22/03/2017.
 */
public class JsonMapperTest {

    @Test
    public void testFromJson() {
        //language=JSON
        String json = "[{\"a\":\"a\", \"b\":\"b\"}]";
        final List<TestObj> list = JsonMapper.getInstance().fromJson(json, List.class, TestObj.class);
        assertEquals(1, list.size());
        assertEquals("a", list.get(0).getA());
    }

    @Test
    public void testFromJsonForCollection() {
        JsonMapper instance = JsonMapper.getInstance();
        JavaType collectionType = instance.createCollectionType(List.class, TestObj.class);
        String json = "[{\"a\":\"a\", \"b\":\"b\"}]";
        final List<TestObj> list = instance.fromJson(json, collectionType);
        assertEquals(1, list.size());
        assertEquals("a", list.get(0).getA());
    }

    @Test
    public void testForGeneralType() {
        String json = "{\"t\":{\"a\":\"a\",\"b\":\"b\"}}";
        JsonMapper instance = JsonMapper.getInstance();
        JavaType type = instance.createJavaType(TestWapper.class, TestObj.class);
        final TestWapper<TestObj> list = instance.fromJson(json, type);
        assertEquals("a", list.t.getA());
    }

    @Test
    public void testJsonNaming() {
        final String fieldOne = "fieldOne";
        final String fieldTwo = "fieldTwo";
        final ObjWithJsonNaming objWithJsonNaming = new ObjWithJsonNaming()
                .setFieldOne(fieldOne)
                .setFieldTwo(fieldTwo);
        final ObjNoJsonNaming objNoJsonNaming = new ObjNoJsonNaming()
                .setFieldOne(fieldOne)
                .setFieldTwo(fieldTwo);

        final String json = JsonMapper.getInstance().toJson(objWithJsonNaming);
        System.out.println(json);
        final SnakeObj snakeObj = JsonMapper.getInstance().fromJson(json, SnakeObj.class);
        System.out.println(snakeObj);
        System.out.println(JsonMapper.getInstance().toJson(objNoJsonNaming));
    }

    @Data
    @Accessors(chain = true)
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class ObjWithJsonNaming {
        private String fieldOne;
        private String fieldTwo;
    }

    @Data
    @JsonNaming
    public static class SnakeObj {
        private String field_one;
        private String field_two;
    }

    @Data
    @Accessors(chain = true)
    public static class ObjNoJsonNaming {
        private String fieldOne;
        private String fieldTwo;
    }

    @Data
    public static class TestWapper<T> {
        private T t;
    }

    @Data
    public static class TestObj {
        private String  a;
        private String b;
    }
}
