package com.vbobot.common.utils.dao.jpa.convert;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

/**
 * @author Bobo
 * @date 2023/4/23
 */
public class ListIntegerToJsonConvertorTest {

    @Test
    public void testName() {
        final ArrayList<Integer> attribute = new ArrayList<>();
        attribute.add(1);
        attribute.add(2);
        final ListIntegerToJsonConvertor convertor = new ListIntegerToJsonConvertor();
        final String s = convertor.convertToDatabaseColumn(attribute);
        assertEquals("[1,2]",  s);
        assertEquals(attribute, convertor.convertToEntityAttribute("[1,2]"));
    }

}