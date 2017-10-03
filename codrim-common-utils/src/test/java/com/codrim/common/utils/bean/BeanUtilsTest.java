package com.codrim.common.utils.bean;

import com.codrim.common.utils.enums.DateFormat;
import com.codrim.common.utils.enums.TrueFalse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
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
        assertEquals(DateFormat.yyyy_MM_dd_HH_mm_ss.getKey(), populateTest2.getDateFormat().getKey());
        assertEquals(TrueFalse.False.getKey(), populateTest2.getIsTrue().getKey());

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

    public static class PopulateTest {
        private String a;
        private Integer b;
        private String c;
        private int d;
        private int isTrue;
        private Integer dateFormat;


        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getIsTrue() {
            return isTrue;
        }

        public void setIsTrue(int isTrue) {
            this.isTrue = isTrue;
        }

        public Integer getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(Integer dateFormat) {
            this.dateFormat = dateFormat;
        }
    }

    public static class PopulateTest2 {
        private String a;
        private Integer b;
        private String c;
        private int d;
        private TrueFalse isTrue;
        private DateFormat dateFormat;


        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public TrueFalse getIsTrue() {
            return isTrue;
        }

        public void setIsTrue(TrueFalse isTrue) {
            this.isTrue = isTrue;
        }

        public DateFormat getDateFormat() {
            return dateFormat;
        }

        public void setDateFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }
    }


}
