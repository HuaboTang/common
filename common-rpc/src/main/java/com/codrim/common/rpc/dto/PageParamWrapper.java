package com.codrim.common.rpc.dto;

import com.codrim.common.utils.bean.BeanUtils;
import com.codrim.common.utils.web.vo.PagingParam;

import java.io.Serializable;

/**
 * DTO层为参数添加分页参数的包装类
 * @author liang.ma on 27/11/2017.
 */
public final class PageParamWrapper<T> implements Serializable {
    private static final int MAX__PAGE_ROWS = 1000;
    /** 当前页数 */
    private int page = 1;
    /** 每页最大行数 */
    private int rows = 10;
    private T param;

    public int getPage() {
        return page;
    }

    private void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    private void setRows(int rows) {
        this.rows = rows > MAX__PAGE_ROWS ? MAX__PAGE_ROWS : rows;
    }

    public T getParam() {
        return param;
    }

    private void setParam(T param) {
        this.param = param;
    }

    public PageParamWrapper() {}

    public static <T> PageParamWrapper<T> newInstance(int page, int rows, T t) {
        PageParamWrapper<T> pageParamWrapper = new PageParamWrapper<>();
        pageParamWrapper.setRows(rows);
        pageParamWrapper.setPage(page);
        pageParamWrapper.setParam(t);
        return pageParamWrapper;
    }

    public static <T> PageParamWrapper<T> newInstance(PagingParam pagingParam, Class<T> t){
        PageParamWrapper<T> pageParamWrapper = new PageParamWrapper<>();
        pageParamWrapper.setPage(pagingParam.getPage());
        pageParamWrapper.setRows(pagingParam.getRows());
        pageParamWrapper.param = BeanUtils.copyProperties(pagingParam, t);
        return pageParamWrapper;
    }


}
