package com.codrim.common.utils.web.vo.serializer;

import com.codrim.common.utils.enums.EnumWithKeyDesc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用此注解表明被注解元素使用的枚举类型
 * @author liang.ma
 */
@Target(value={ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumTypeSpecify {

    Class<? extends EnumWithKeyDesc> using();
}
