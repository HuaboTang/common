package com.vbobot.common.utils.web.vo.serializer;

import com.vbobot.common.utils.enums.EnumWithKeyDesc;

/**
 *
 * Created by ZhugeLiang on 14/02/2017.
 */
class EnumToString<Key> {
    private String desc;
    private Key key;

    EnumToString(EnumWithKeyDesc<Key> keyDesc) {
        this.desc = keyDesc.getDesc();
        this.key = keyDesc.getKey();
    }

    EnumToString(String desc, Key key) {
        this.desc = desc;
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
