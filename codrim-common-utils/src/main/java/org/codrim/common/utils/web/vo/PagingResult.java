package org.codrim.common.utils.web.vo;

/**
 * 分页查询结果
 */
public class PagingResult<T> {
    private int total;
    private T rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
