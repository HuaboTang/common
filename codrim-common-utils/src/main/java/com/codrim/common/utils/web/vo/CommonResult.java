package com.codrim.common.utils.web.vo;

import com.codrim.common.utils.enums.ResultCode;
import com.sun.net.httpserver.Authenticator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;


/**
 * 通用返回结果
 * Created by tanghuabo on 9/22/16.
 */
public class CommonResult<T> implements Serializable {
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int result = ResultCode.Success.key;
    private String msg = ResultCode.Success.desc;
    private T data;

    public CommonResult() {}

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

    public CommonResult(T t) {
        this.data = t;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
