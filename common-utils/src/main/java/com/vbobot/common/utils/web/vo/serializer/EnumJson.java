package com.vbobot.common.utils.web.vo.serializer;

/**
 *
 * Created by ZhugeLiang on 10/02/2017.
 */
class EnumJson {
    private Object key;
    private Object desc;

    public EnumJson(Object key, Object desc) {
        this.key = key;
        this.desc = desc;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }
}
