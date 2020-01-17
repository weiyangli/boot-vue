package com.boot.bvserver.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用自定义注解插入接口日志
 *
 * @author lwy
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)    // 明确注解生命周期 SOURCE < CLASS < RUNTIME ，前者能作用的地方后者一定也能作用。
@Documented
public @interface LogInfo {
    String value() default "";         // 日志描述
}
