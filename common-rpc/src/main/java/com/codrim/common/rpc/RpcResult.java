package com.codrim.common.rpc;

import com.codrim.common.utils.enums.ResultCode;

import java.io.Serializable;

/**
 * Rpc公共返回值包装对象
 *
 * Rpc统一使用状态码及状态信息来处理异常和传递数据,不使用抛出异常来处理错误,状态值返回100时,表示处理成功,其它则为失败
 *
 * Created by ZhugeLiang on 13/02/2017.
 */
public class RpcResult<T extends Serializable> implements Serializable {
    /** 状态值 */
    private Integer result = ResultCode.SUCCESS;
    private String msg;
    private T data;

    public RpcResult() {
    }

    public RpcResult(int errorCode, String errorMessage) {
        this.result = errorCode;
        this.msg = errorMessage;
    }

    public RpcResult(T t) {
        this.result = ResultCode.SUCCESS;
        this.msg = "success";
        this.data = t;
    }

    /**
     * 返回的结果是否代表操作成功
     * @return 返回true,如果操作成功;否则返回false
     */
    public boolean isSuccess() {
        return ResultCode.Success.getKey().equals(this.result);
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
