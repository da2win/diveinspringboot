package com.da2win.diveinspringboot.annotation;

/*** 一级 {@link Repository @Repository}** @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>* @since 1.0.0*/

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repository
public @interface FirstLevelRepository {

    String value() default "";
}

