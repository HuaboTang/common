package com.codrim.common.utils.web.vo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Null、空串、逗号分隔的数字
 * Created by liang.ma on 12/11/2016.
 */
@Constraint(validatedBy = NullOrEmptyStringOrNumberSeperatorByCommaValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NullOrEmptyStringOrNumberSeperatorByComma {
    String message() default "{}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
