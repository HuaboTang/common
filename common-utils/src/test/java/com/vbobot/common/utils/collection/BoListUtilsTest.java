package com.vbobot.common.utils.collection;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Liang.Zhuge
 * @date 2020/9/24
 */
public class BoListUtilsTest {

    @Test
    public void testMap() {
        final List<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        final List<String> result = BoListUtils.map(objects, String::valueOf);
        assertEquals("2", result.get(1));

        assertTrue(CollectionUtils.isEmpty(BoListUtils.map(new ArrayList<>(), String::valueOf)));
    }
}
