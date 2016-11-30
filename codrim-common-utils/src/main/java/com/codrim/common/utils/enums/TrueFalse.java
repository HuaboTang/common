package com.codrim.common.utils.enums;

/**
 * Created by liang.ma on 12/11/2016.
 */
public enum TrueFalse implements EnumWithKey {
    True(TrueFalse.TRUE, "true"),
    False(TrueFalse.FALSe, "false");

    public static final int TRUE = 1;
    public static final int FALSe = 0;

    public final int key;
    public final String desc;

   private TrueFalse(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public int getKey() {
        return 0;
    }

    public static TrueFalse trueFalseEnum(int key) {
        return EnumUtils.enumForKey(TrueFalse.class, key);
    }
}
