package com.codrim.common.utils.web.vo;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页查询结果
 */
public class PagingResult<T> implements Serializable {
    private int total;
    private Collection<T> rows;

    public PagingResult() {
    }

    public PagingResult(int total, Collection<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<T> getRows() {
        return rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }
}
