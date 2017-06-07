package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.enums.EnumWithKey;
import com.codrim.common.utils.json.JsonMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Created by liang.ma on 07/06/2017.
 */
public class EnumWithKeySerializerTest {

    @Test
    public void test() {
        String json = JsonMapper.nonEmptyMapper().toJson(new TestClass(1, TestEnum.Test1, "test"));
        Assert.assertTrue(json.contains("\"testEnum\":1"));
    }

    enum TestEnum implements EnumWithKey<Integer>{
        Test1(1),
        Test2(2);

        public final int key;
        TestEnum(int key) {
            this.key = key;
        }
        @Override
        public Integer getKey() {
            return key;
        }
    }

    class TestClass {

        private Integer id;
        @JsonSerialize(using = EnumWithKeySerializer.class)
        private TestEnum testEnum;
        private String name;

        TestClass(Integer id, TestEnum testEnum, String name) {
            this.id = id;
            this.testEnum = testEnum;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public TestEnum getTestEnum() {
            return testEnum;
        }

        public void setTestEnum(TestEnum testEnum) {
            this.testEnum = testEnum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
