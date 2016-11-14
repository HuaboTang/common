package org.codrim.common.utils.enums;

/**
 * Created by liang.ma on 12/11/2016.
 */
public enum TrueFalseEnum implements EnumWithKey {
    TrueEnum(1, "true"),
    FalseEnum(0, "false");

    public static final int True = 1;
    public static final int False = 0;

    public final int key;
    public final String desc;

   private TrueFalseEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public int getKey() {
        return 0;
    }

    public static TrueFalseEnum trueFalseEnum(int key) {
        return EnumUtils.enumForKey(TrueFalseEnum.class, key);
    }
}
