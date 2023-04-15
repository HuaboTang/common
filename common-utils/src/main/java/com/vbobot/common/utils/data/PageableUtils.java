package com.vbobot.common.utils.data;

import com.vbobot.common.utils.web.vo.PagingParam;
import com.vbobot.common.utils.web.vo.PagingResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
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
        final int row = rows(param);
        int page = page(param);

        return param.hasSort() ? sortFromPagingParam(param, row, page)
                               : PageRequest.of(page, row);

    }

    private static int page(PagingParam param) {
        return Math.max(0, param.getPage() - 1);
    }

    private static int rows(PagingParam param) {
        return Math.max(0, Math.min(500, param.getRows()));
    }

    private static PageRequest sortFromPagingParam(PagingParam param, int row, int page) {
        final Sort.Direction direction = param.getDirection() == PagingParam.DIRECTION_ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(page, row, direction, param.getProperties().toArray(new String[0]));
    }

    public static Pageable toPageable(PagingParam param, Supplier<Sort> defSortCreator) {
        if (param == null) {
            param = new PagingParam();
        }
        final int row = rows(param);
        int page = page(param);

        if (param.hasSort()) {
            return sortFromPagingParam(param, row, page);
        } else {
            Sort defSort;
            if (defSortCreator != null && (defSort = defSortCreator.get()) != null) {
                return PageRequest.of(page, row, defSort);
            } else {
                return PageRequest.of(page, row);
            }
        }
    }

    public static Pageable toPageable(PagingParam param, Sort sort) {
        if (param == null) {
            param = new PagingParam();
        }
        final int row = rows(param);
        return PageRequest.of(page(param), row, sort);
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
