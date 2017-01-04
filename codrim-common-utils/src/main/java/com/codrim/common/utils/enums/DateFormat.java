package com.codrim.common.utils.enums;

/**
 * 日期格式化枚举类
 * Created by liang.ma on 03/11/2016.
 */
public enum DateFormat implements EnumWithKey<Integer> {
    yyyy_MM_dd(DateFormat.YYYY_MM_DD, "yyyy-MM-dd"),
    yyyyMMdd(DateFormat.YYYYMMDD, "yyyyMMdd"),
    yyyy_MM_dd_HH_mm_ss(DateFormat.YYYY_MM_DD_HH_MM_SS, "yyyy-MM-dd HH:mm:ss"),
    yyyyMMddHHmmss(DateFormat.YYYYMMDDHHMMSS, "yyyyMMddHHmmss");

    public static final int YYYY_MM_DD = 10;
    public static final int YYYYMMDD = 11;
    public static final int YYYY_MM_DD_HH_MM_SS = 20;
    public static final int YYYYMMDDHHMMSS = 21;


    private final int key;
    private final String desc;

    DateFormat(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    public String getDesc() {return desc;}

    public static DateFormat dateFormatEnum(int key) {
        return EnumUtils.enumForKey(DateFormat.class, key);
    }

}
