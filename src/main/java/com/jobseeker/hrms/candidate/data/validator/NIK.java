package com.jobseeker.hrms.candidate.data.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NIKValidator.class)
public @interface NIK {
    String message() default "Invalid NIK format";
    boolean isRequired() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}