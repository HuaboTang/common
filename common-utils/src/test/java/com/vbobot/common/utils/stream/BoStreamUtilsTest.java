package com.vbobot.common.utils.stream;

import com.vbobot.common.utils.web.vo.KeyAndValue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Liang.Zhuge
 * @date 2021/1/4
 */
public class BoStreamUtilsTest {
    @Test
    public void testToMap() {
        final ArrayList<KeyAndValue<String, Integer>> objects = new ArrayList<>();
        objects.add(createKeyAndValue("1", 1));
        objects.add(createKeyAndValue("2", 2));
        objects.add(createKeyAndValue("3", 3));
        objects.add(createKeyAndValue("4", 4));
        objects.add(createKeyAndValue("5", 5));
        objects.add(createKeyAndValue("6", 6));

        final Map<String, Integer> map = objects.stream().collect(BoStreamUtils.toMap(KeyAndValue::getKey, KeyAndValue::getValue));
        assertEquals(6, map.keySet().size());
        assertEquals(5, map.get("5").intValue());
    }

    private KeyAndValue<String, Integer> createKeyAndValue(String key, Integer value) {
        final KeyAndValue<String, Integer> kv = new KeyAndValue<>();
        kv.setKey(key);
        kv.setValue(value);
        return kv;
    }

}
