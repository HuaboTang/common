package com.codrim.common.utils.web.vo.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by liang.ma on 12/11/2016.
 */
public class NullOrEmptyStringOrNumberSeperatorByCommaValidator  implements ConstraintValidator<NullOrEmptyStringOrNumberSeperatorByComma, String>{
    @Override
    public void initialize(NullOrEmptyStringOrNumberSeperatorByComma constraintAnnotation) {

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
