package com.codrim.common.utils.web.vo;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Param for paging
 * 页面查询,传递page和rows参数;数据库查询时,传递offset和rows作为查询参数
 */
public class PagingParam implements Serializable {
    public static final int MAX__PAGE_ROWS = 1000;

    public static final int DIRECTION_ASC = 1;
    public static final int DIRECTION_DESC = 2;

    /** 当前页数 */
    private int page = 1;
    /** 每页最大行数 */
    private int rows = 10;

    private int direction;
    private List<String> properties;

    public void putSort(Sort.Direction direction, String... properties) {
        this.direction = direction == Sort.Direction.DESC ? DIRECTION_DESC : DIRECTION_ASC;
        this.properties = Arrays.asList(properties);
    }

    public boolean hasSort() {
        return (direction == 1 || direction == 2) && CollectionUtils.isNotEmpty(properties);
    }

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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }
}
