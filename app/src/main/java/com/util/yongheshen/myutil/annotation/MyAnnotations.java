package com.util.yongheshen.myutil.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 水果名称注解
 * Created by Diablo on 16/4/26.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotations {
    String name() default "";
    String color() default "blue";
}
