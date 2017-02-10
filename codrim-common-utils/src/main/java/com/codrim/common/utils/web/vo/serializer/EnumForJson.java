package com.codrim.common.utils.web.vo.serializer;

/**
 *
 * Created by ZhugeLiang on 10/02/2017.
 */
class EnumForJson {
    private Object key;
    private Object desc;

    public EnumForJson(Object key, Object desc) {
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
