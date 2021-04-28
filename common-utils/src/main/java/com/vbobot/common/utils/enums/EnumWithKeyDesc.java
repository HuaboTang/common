package com.vbobot.common.utils.enums;

/**
 * key,value接口
 * Created by liang.ma on 08/12/2016.
 */
public interface EnumWithKeyDesc<T> extends EnumWithKey<T> {

    String getDesc();
}
