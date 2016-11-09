package org.codrim.common.utils.web.vo;

import static org.codrim.common.utils.web.vo.CommonResult.ResultEnum.ErrorEnum;
import static org.codrim.common.utils.web.vo.CommonResult.ResultEnum.SuccessEnum;

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
        this.result = ResultEnum.ErrorEnum.key;
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
    public static enum ResultEnum {
        SuccessEnum(100, "处理成功"),
        ErrorEnum(101, "处理失败");

        public final int key;
        public final String desc;

        ResultEnum(int key, String desc) {
            this.key = key;
            this.desc = desc;
        }
        public int getKey() {
            return key;
        }
        public String getDesc() {
            return desc;
        }
    }
}
