package com.vbobot.common.utils.web.vo;

import com.vbobot.common.utils.enums.EnumWithKeyDesc;

import lombok.Data;

/**
 * @author Liang.Zhuge
 * @date 2020/12/11
 */
@Data
public class KeyAndValue<K, V> {
    private K key;
    private V value;

    public static <K> KeyAndValue<K, String> of(EnumWithKeyDesc<K> e) {
        final KeyAndValue<K, String> result = new KeyAndValue<>();
        result.setKey(e.getKey());
        result.setValue(e.getDesc());
        return result;
    }
}
