package org.codrim.common.utils.enums;

public enum ResultEnum implements EnumWithKey {

    SuccessEnum(100, "处理成功"),
    ErrorEnum(101, "处理失败");

    public final int key;
    public final String desc;

    private ResultEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public int getKey() {
        return key;
    }
}
