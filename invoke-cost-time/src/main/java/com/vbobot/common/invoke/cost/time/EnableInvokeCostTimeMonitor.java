package com.vbobot.common.invoke.cost.time;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * @author Bobo
 * @date 2021/8/3
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(EnableInvokeCostTimeRegistrar.class)
public @interface EnableInvokeCostTimeMonitor {

    String[] value() default {};

    String[] basePackages() default {};
}
