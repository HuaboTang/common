package com.codrim.common.utils.enums;

import java.io.Serializable;

public enum ResultCode implements EnumWithKeyDesc<Integer>, Serializable {

    Success(ResultCode.SUCCESS, "处理成功"),
    Error(ResultCode.ERROR, "处理失败");

    public static final int ERROR = 101;
    public static final int SUCCESS = 100;

    public final int key;
    public final String desc;

    ResultCode(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public Integer getKey() {
        return key;
    }
}
