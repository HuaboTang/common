package com.vbobot.common.utils.data;

import com.vbobot.common.utils.web.vo.PagingParam;

import org.junit.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Liang.Zhuge
 * @date 2020/6/9
 */
public class PageableUtilsTest {

    @Test
    public void testToPageable() throws Exception {
        final PagingParam pagingParam = new PagingParam();
        Supplier<Sort> sortSupplier = null;
        final Pageable pageable = PageableUtils.toPageable(pagingParam, sortSupplier);
        assertNull(pageable.getSort());

        pagingParam.putSort(Sort.Direction.DESC, "test");
        final Pageable pageable1 = PageableUtils.toPageable(pagingParam, sortSupplier);
        final Sort sort1 = pageable1.getSort();
        assertNotNull(sort1);
        assertEquals("test", sort1.getOrderFor("test").getProperty());

        pagingParam.setProperties(null);
        pagingParam.setDirection(0);
        final Pageable pageable2 = PageableUtils.toPageable(pagingParam, () -> Sort.by(Sort.Direction.DESC, "test2"));
        final Sort sort2 = pageable2.getSort();
        assertNotNull(sort2);
        assertEquals("test2", sort2.getOrderFor("test2").getProperty());
    }
}
