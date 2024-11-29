package com.jobseeker.hrms.candidate.data.validator.education.major;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MajorValidator.class)
public @interface MajorValidation {
    String message() default "Education level doesn't have major";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // Custom attributes to pass field names
    String checkBy();
    String target();
}