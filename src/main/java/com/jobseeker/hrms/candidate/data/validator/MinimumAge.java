package com.jobseeker.hrms.candidate.data.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinimumAgeValidator.class)
public @interface MinimumAge {
    String message() default "Must be at least {value} years old";
    int value() default 0;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}