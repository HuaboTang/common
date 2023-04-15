package com.vbobot.common.utils.web.vo;

import com.vbobot.common.utils.enums.ResultCode;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.validation.constraints.NotNull;

import lombok.Data;


/**
 * 通用返回结果
 * Created by tanghuabo on 9/22/16.
 */
@Data
public class CommonResult<T> implements Serializable {
    private int result = ResultCode.Success.key;
    private String msg = ResultCode.Success.desc;
    private T data;

    public CommonResult() {}

    @Deprecated
    public CommonResult(String errorMsg) {
        this.result = ResultCode.Error.key;
        this.msg = errorMsg;
    }

    public CommonResult(int errorCode, String errorMsg) {
        this.result = errorCode;
        this.msg = errorMsg;
    }

    public CommonResult(Throwable e) {
        this.result = ResultCode.Error.key;
        this.msg = e.getClass() + "==>" +e.getMessage();
    }

    @Deprecated
    public CommonResult(T t) {
        this.data = t;
    }

    public static <T> CommonResult<T> error(int errorCode, String errorMsg) {
        final CommonResult<T> result = new CommonResult<>();
        result.result = errorCode;
        result.msg = errorMsg;
        return result;
    }

    public static <T> CommonResult<T> success() {
        return success(null);
    }

    public static <T> CommonResult<T> success(T t) {
        final CommonResult<T> result = new CommonResult<>();
        result.result = ResultCode.Success.key;
        result.setData(t);
        return result;
    }

    public static <T, R> CommonResult<List<R>> success(List<T> data, @NotNull Function<T, R> converter) {
        final CommonResult<List<R>> result = new CommonResult<>();
        result.result = ResultCode.Success.key;
        if (data != null) {
            result.setData(data.stream().map(converter).collect(Collectors.toList()));
        }
        return result;
    }

    public <R> CommonResult<R> map(@NotNull Function<T, R> converter) {
        if (converter == null) {
            throw new NullPointerException();
        }

        final CommonResult<R> result = new CommonResult<>();
        result.setResult(this.result);
        result.setMsg(this.msg);
        if (data != null) {
            result.setData(converter.apply(this.data));
        }
        return result;
    }
}
