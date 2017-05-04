package com.codrim.common.utils.enums;

import java.io.Serializable;

public enum ResultCode implements EnumWithKeyDesc<Integer>, Serializable {

    Success(ResultCode.SUCCESS, "处理成功"),
    Error(ResultCode.ERROR, "处理失败"),
    NoPermission(ResultCode.NO_PERMISSION, "无权限"),
    NoResource(ResultCode.NO_RESOURCE, "资源不存在");

    public static final int ERROR = 500;
    public static final int SUCCESS = 200;
    public static final int NO_PERMISSION = 403;
    public static final int NO_RESOURCE = 404;
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
