package org.codrim.common.utils.web.vo;

import static org.codrim.common.utils.enums.ResultEnum.ErrorEnum;
import static org.codrim.common.utils.enums.ResultEnum.SuccessEnum;

/**
 * 通用返回结果
 * Created by tanghuabo on 9/22/16.
 */
public class CommonResult<T> {
    private int result = SuccessEnum.key;
    private String msg = SuccessEnum.desc;
    private T data;

    public CommonResult() {}

    public CommonResult(String errorMsg) {
        this.result = ErrorEnum.key;
        this.msg = errorMsg;
    }

    public CommonResult(int errorCode, String errorMsg) {
        this.result = errorCode;
        this.msg = errorMsg;
    }

    public CommonResult(Throwable e) {
        this.result = ErrorEnum.key;
        this.msg = e.getMessage();
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
