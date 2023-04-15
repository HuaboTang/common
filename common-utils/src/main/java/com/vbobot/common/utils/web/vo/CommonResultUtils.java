package com.vbobot.common.utils.web.vo;

import com.vbobot.common.utils.exception.Assert;

import jakarta.validation.constraints.NotNull;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author Liang.Zhuge
 * @date 2019-09-24
 */
public class CommonResultUtils {

    public static void checkResult(CommonResult<?> result) {
        Assert.isTrue(result.getResult() == 200, result.getResult(), result.getMsg());
    }

    public static <T, R> CommonResult<List<R>> map(CommonResult<List<T>> originResult, @NotNull Function<T, R> converter) {
        if (converter == null) {
            throw new NullPointerException();
        }

        if (originResult == null || CollectionUtils.isEmpty(originResult.getData())) {
            return CommonResult.success();
        }

        return originResult.map(item -> item.stream().map(converter).collect(Collectors.toList()));
    }
}
