package com.vbobot.common.utils.web.vo.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Null、空串、逗号分隔的数字
 * Created by liang.ma on 12/11/2016.
 */
@Constraint(validatedBy = BlankOrNumberSeperatorByCommaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BlankOrNumberSeperatorByComma {
    String message() default "{}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
