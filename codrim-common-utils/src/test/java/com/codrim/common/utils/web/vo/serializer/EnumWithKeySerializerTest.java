package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.enums.EnumWithKeyDesc;
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
        String json = JsonMapper.nonEmptyMapper().toJson(new EnumSerailizeClass(1, IntegerKeyEnum.Test1, "test"));
        Assert.assertTrue(json.contains("\"key\":1"));
    }

    @Test
    public void test2() {
        String json = JsonMapper.nonEmptyMapper().toJson(new ShortKeyEnumSerializeClass(1, (short)3, "test"));
        String json2 = JsonMapper.nonEmptyMapper().toJson(new IntegerKeyEnumSerializeClass(1, 1, "test"));
        Assert.assertTrue(json.contains("\"key\":3"));
        Assert.assertTrue(json2.contains("\"key\":1"));
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

    private enum ShortKeyEnum implements EnumWithKeyDesc<Short> {
        Test3((short)3,"test3"),
        Test4((short)4, "test4");

        public final short key;
        public final String desc;
        ShortKeyEnum(short key, String desc) {
            this.key = key;
            this.desc = desc;
        }
        @Override
        public Short getKey() {
            return key;
        }

        @Override
        public String getDesc() {
            return desc;
        }
    }

    private class EnumSerailizeClass {

        private Integer id;
        @JsonSerialize(using = EnumWithKeyDescSerializer.class)
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



    private class IntegerKeyEnumSerializeClass {

        private Integer id;
        @JsonSerialize(using = NumberEnumKeySerializer.class)
        @EnumTypeSpecify(using = IntegerKeyEnum.class)
        private int key;
        private String name;

        IntegerKeyEnumSerializeClass(Integer id, int key, String name) {
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

    private class ShortKeyEnumSerializeClass {

        private Integer id;
        @JsonSerialize(using = NumberEnumKeySerializer.class)
        @EnumTypeSpecify(using = ShortKeyEnum.class)
        private short key;
        private String name;

        ShortKeyEnumSerializeClass(Integer id, short key, String name) {
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

        public short getKey() {
            return key;
        }

        public void setKey(short key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
