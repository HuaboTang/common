package com.vbobot.common.utils.web.vo.validators;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * Created by liang.ma on 12/11/2016.
 */
public class BlankOrNumberSeperatorByCommaValidator  implements ConstraintValidator<BlankOrNumberSeperatorByComma, String>{
    @Override
    public void initialize(BlankOrNumberSeperatorByComma constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            value = value.trim();
            if (!"".equals(value)){
                return Pattern.matches("^\\d+(,\\d+)*", value);
            }
        }
        return true;
    }
}
