package org.codrim.common.utils.web.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.*;

import java.util.Set;

import static org.codrim.common.utils.enums.ResultEnum.ErrorEnum;
import static org.codrim.common.utils.enums.ResultEnum.SuccessEnum;

/**
 * 通用返回结果
 * Created by tanghuabo on 9/22/16.
 */
public class CommonResult<T> {
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

    public static void main(String[] args) {
        CommonResult<String> commonResult = new CommonResult<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CommonResult<String>>> constraintValidatorSet = validator.validate(commonResult);
        constraintValidatorSet.size();
        System.out.println(constraintValidatorSet.iterator().next().getMessage());
    }
}
