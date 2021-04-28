package com.vbobot.common.utils.bean;

import com.vbobot.common.utils.enums.DateFormat;
import com.vbobot.common.utils.enums.TrueFalse;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Unit testEnumSerializer for {@link BeanUtils}
 * Created by ZhugeLiang on 10/03/2017.
 */
public class BeanUtilsTest {

    @Test
    public void copyPropertiesAndConvertkeyToEnum() throws Exception {
        final PopulateTest populateTest = new PopulateTest();
        populateTest.setA("a");
        populateTest.setB(10);
        populateTest.setC("C");
        populateTest.setD(10);
        populateTest.setIsTrue(0);
        populateTest.setDateFormat(20);

        final PopulateTest2 populateTest2 = BeanUtils.copyPropertiesAndConvertKeyToEnum(populateTest, PopulateTest2.class);
        assertEquals("a", populateTest2.getA());
        assertEquals((Integer) 10, populateTest2.getB());
        assertEquals("C", populateTest2.getC());
        assertEquals(10, populateTest2.getD());
        Assert.assertEquals(DateFormat.yyyy_MM_dd_HH_mm_ss.getKey(), populateTest2.getDateFormat().getKey());
        Assert.assertEquals(TrueFalse.False.getKey(), populateTest2.getIsTrue().getKey());

    }

    @Test
    public void testCopyProperties() throws Exception {
        final PopulateTest populateTest = new PopulateTest();
        populateTest.setA("a");
        populateTest.setB(10);
        populateTest.setC("C");
        populateTest.setD(10);
        populateTest.setIsTrue(0);
        populateTest.setDateFormat(20);

        final PopulateTest2 populateTest2 = BeanUtils.copyProperties(populateTest, PopulateTest2.class);
        assertEquals("a", populateTest2.getA());
        assertEquals((Integer) 10, populateTest2.getB());
        assertEquals("C", populateTest2.getC());
        assertEquals(10, populateTest2.getD());
        assertEquals(null, populateTest2.getDateFormat());
        assertEquals(null, populateTest2.getIsTrue());

    }

    @Test
    public void populate() throws Exception {
        final Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", 10);
        map.put("c", 10);
        map.put("d", 10);
        final PopulateTest populate = BeanUtils.populate(map, PopulateTest.class);
        assertEquals("a", populate.getA());
        assertEquals((Integer) 10, populate.getB());
        assertNull(populate.getC());
        assertEquals(10, populate.getD());
    }

    @Test
    public void testCopyPropertiesAndConvertEnumToKey() {
        final PopulateTest2 source = new PopulateTest2();
        source.a = "a";
        source.b = 1;
        source.c = "c";
        source.d = 2;
        source.isTrue = TrueFalse.True;
        source.dateFormat = DateFormat.yyyy_MM_dd;

        final PopulateTest entity2 = BeanUtils.copyPropertiesAndConvertEnumToKey(source, PopulateTest.class);
        assertNotNull(entity2);
        assertEquals("a", entity2.getA());
        assertEquals(1, entity2.getB().intValue());
        assertEquals("c", entity2.getC());
        assertEquals(2, entity2.getD());
        assertEquals(TrueFalse.TRUE, entity2.getIsTrue());
        assertEquals(DateFormat.YYYY_MM_DD, entity2.getDateFormat().intValue());
    }

    @Data
    public static class PopulateTest {
        private String a;
        private Integer b;
        private String c;
        private int d;
        private int isTrue;
        private Integer dateFormat;
    }

    @Data
    public static class PopulateTest2 {
        private String a;
        private Integer b;
        private String c;
        private int d;
        private TrueFalse isTrue;
        private DateFormat dateFormat;

    }


}
