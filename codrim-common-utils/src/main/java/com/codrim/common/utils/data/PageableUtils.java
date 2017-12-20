package com.codrim.common.utils.data;

import com.codrim.common.utils.web.vo.PagingParam;
import com.codrim.common.utils.web.vo.PagingResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utils for Pageable
 *
 * @author ZhugeLiang
 * @date 08/03/2017
 */
public class PageableUtils {

    /**
     * Create `Pageable` entity from PagingParam
     *
     * @param param 分页参数,可为空
     * @return Pageable entity
     */
    public static Pageable toPageable(PagingParam param) {
        if (param == null) {
            param = new PagingParam();
        }
        final int row = Math.max(0, Math.min(500, param.getRows()));
        return new PageRequest(Math.max(0, param.getPage() - 1), row);
    }

    public static Pageable toPageable(PagingParam param, Sort sort) {
        if (param == null) {
            param = new PagingParam();
        }
        final int row = Math.max(0, Math.min(500, param.getRows()));
        return new PageRequest(Math.max(0, param.getPage() - 1), row, sort);
    }

    public static <T> PagingResult<T> toPagingResult(Page<T> page) {
        return new PagingResult<>(((Number) page.getTotalElements()).intValue(), page.getContent());
    }

    public static <T, R> PagingResult<R> toPagingResult(Page<T> page, Function<? super T, ? extends R> mapper) {
        return new PagingResult<>(((Number) page.getTotalElements()).intValue(),
                page.getContent().stream().map(mapper).collect(Collectors.toList()));
    }

    public static <T, R> PagingResult<R> toPagingResultWithListMapper(Page<T> page, Function<List<T>, List<R>> mapper) {
        return new PagingResult<>(((Number) page.getTotalElements()).intValue(),
                mapper.apply(page.getContent()));
    }
}
