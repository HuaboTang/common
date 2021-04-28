package com.vbobot.common.utils.web.vo.serializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vbobot.common.utils.enums.EnumWithKeyDesc;
import com.vbobot.common.utils.json.JsonMapper;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Created by liang.ma on 07/06/2017.
 */
public class EnumWithKeyToKeySerializerTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testEnumSerializer() {
        String json = JsonMapper.nonEmptyMapper().toJson(new EnumSerailizeClass(1, IntegerKeyEnum.Test1, "testEnumSerializer"));
        System.out.println("IntegerKeyEnum:" + json);
        Assert.assertTrue(json.contains("\"key\":1"));
    }

    @Test
    public void testKeySerializer() {
        String json = JsonMapper.nonEmptyMapper().toJson(new IntegerKeySerializeClass(1, 1, "testEnumSerializer"));
        System.out.println("KeySerialize:" + json);
        Assert.assertTrue(json.contains("\"key\":1"));
    }

    @Test
    public void testKeysSerializer() {
        String json = JsonMapper.nonEmptyMapper().toJson(new IntegerKeysSerializerClass(1, "1,2" , "testEnumSerializer"));
        System.out.println("KeysSerialize:" +  json);
        Assert.assertTrue(json.contains("\"key\":1"));
        Assert.assertTrue(json.contains("\"key\":2"));

    }

    private enum IntegerKeyEnum implements EnumWithKeyDesc<Integer> {
        Test1(1,"test1"),
        Test2(2, "test2");

        public final int key;
        public final String desc;
        IntegerKeyEnum(int key, String desc) {
            this.key = key;
            this.desc = desc;
        }
        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public String getDesc() {
            return desc;
        }
    }


    private class EnumSerailizeClass {

        private Integer id;
        @JsonSerialize(using = EnumWithKeyDescToEnumJsonSerializer.class)
        private IntegerKeyEnum integerKeyEnum;
        private String name;

        EnumSerailizeClass(Integer id, IntegerKeyEnum integerKeyEnum, String name) {
            this.id = id;
            this.integerKeyEnum = integerKeyEnum;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public IntegerKeyEnum getIntegerKeyEnum() {
            return integerKeyEnum;
        }

        public void setIntegerKeyEnum(IntegerKeyEnum integerKeyEnum) {
            this.integerKeyEnum = integerKeyEnum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



    private class IntegerKeySerializeClass {

        private Integer id;
        @JsonSerialize(using = NumberKeyToEnumJsonSerializer.class)
        @EnumTypeSpecify(using = IntegerKeyEnum.class)
        private int key;
        private String name;

        IntegerKeySerializeClass(Integer id, int key, String name) {
            this.id = id;
            this.name = name;
            this.key = key;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private class IntegerKeysSerializerClass {

        private Integer id;
        @JsonSerialize(using = NumberKeysToEnumJsonSerializer.class)
        @EnumTypeSpecify(using = IntegerKeyEnum.class)
        private String keys;
        private String name;

        IntegerKeysSerializerClass(Integer id, String keys, String name) {
            this.id = id;
            this.name = name;
            this.keys = keys;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getKeys() {
            return keys;
        }

        public void setKeys(String keys) {
            this.keys = keys;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
