package org.codrim.common.utils.enums;

/**
 * 日期格式化枚举类
 * Created by liang.ma on 03/11/2016.
 */
public enum DateFormatEnum implements EnumWithKey{
    yyyy_MM_dd_Enum(10, "yyyy-MM-dd"),
    yyyyMMddEnum(11, "yyyyMMdd"),
    yyyy_MM_dd_HH_mm_ss(20, "yyyy-MM-dd HH:mm:ss"),
    yyyyMMddHHmmss(21, "yyyyMMddHHmmss");

    private int key;
    private String desc;

    private DateFormatEnum(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public int getKey() {
        return key;
    }

    public String getDesc() {return desc;}

    public static DateFormatEnum dateFormatEnum(int key) {
        return EnumUtils.enumForKey(DateFormatEnum.class, key);
    }

}
