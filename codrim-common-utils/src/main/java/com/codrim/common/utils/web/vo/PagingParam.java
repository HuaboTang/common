package com.codrim.common.utils.web.vo;

import java.io.Serializable;

/**
 * Param for paging
 * 页面查询,传递page和rows参数;数据库查询时,传递offset和rows作为查询参数
 */
public class PagingParam implements Serializable {
    public static final int MAX__PAGE_ROWS = 100;
    /** 当前页数 */
    private int page = 1;
    /** 每页最大行数 */
    private int rows = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page ;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows > MAX__PAGE_ROWS ? MAX__PAGE_ROWS : rows;
    }

    public int getOffset() {
        return Math.max(page-1, 0) * rows;
    }
}