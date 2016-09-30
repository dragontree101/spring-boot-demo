package com.dragon.study.spring.boot.mvc.annotation;

import com.dragon.study.spring.boot.mvc.validator.Validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by dragon on 16/9/30.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {Validator.class})
public @interface ParamValidator {

  String message() default "Parameter error!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String desc();

  String range() default "";

  boolean isRequired();

}
