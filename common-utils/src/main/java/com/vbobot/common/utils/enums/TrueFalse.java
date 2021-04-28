package com.vbobot.common.utils.enums;

/**
 * Created by liang.ma on 12/11/2016.
 */
public enum TrueFalse implements EnumWithKeyDesc<Integer> {
    True(TrueFalse.TRUE, "true"),
    False(TrueFalse.FALSE, "false");

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    public final int key;
    public final String desc;

    TrueFalse(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    public static TrueFalse trueFalseEnum(int key) {
        return EnumUtils.enumForKey(TrueFalse.class, key);
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
