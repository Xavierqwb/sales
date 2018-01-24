package com.xavier.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xavier on 2018/1/23.
 */
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface MybatisDao {

}
