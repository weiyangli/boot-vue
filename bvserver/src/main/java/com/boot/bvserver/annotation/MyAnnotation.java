package com.boot.bvserver.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lwy
 */
@Target(ElementType.METHOD)            // 声明注解修饰范围
@Retention(RetentionPolicy.RUNTIME)    // 明确注解生命周期 SOURCE < CLASS < RUNTIME ，前者能作用的地方后者一定也能作用。
@Documented
public @interface MyAnnotation {
    String value() default "";
}
