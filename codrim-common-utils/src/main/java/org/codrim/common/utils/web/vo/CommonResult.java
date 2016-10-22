package org.codrim.common.utils.web.vo;

/**
 * 通用返回结果
 * Created by tanghuabo on 9/22/16.
 */
public class CommonResult<T> {
    private String result = Result.Success.value;
    private String msg = "success";
    private T data;

    public CommonResult() {}

    public CommonResult(String errorMsg) {
        this.result = Result.Error.value;
        this.msg = errorMsg;
    }

    public CommonResult(int errorCode, String errorMsg) {
        this.result = String.valueOf(errorCode);
        this.msg = errorMsg;
    }

    public CommonResult(Throwable e) {
        this.result = Result.Error.value;
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

    /**
     * enum for #result
     */
    public static enum Result {
        Success("0000"),
        Error("1000");

        public final String value;

        Result(String value) {
            this.value = value;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
