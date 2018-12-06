package com.codrim.common.utils.web.vo;

import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public <S> PagingResult<S> map(Function<T, S> converter) {
        if (converter == null) {
            throw new IllegalArgumentException();
        }
        if (CollectionUtils.isEmpty(rows)) {
            return new PagingResult<>();
        }
        List<S> collect = rows.stream().map(converter).collect(Collectors.toList());
        return new PagingResult<>(this.total, collect);
    }
}
