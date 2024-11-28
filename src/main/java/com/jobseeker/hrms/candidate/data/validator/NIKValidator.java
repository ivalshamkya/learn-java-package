package com.jobseeker.hrms.candidate.data.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NIKValidator implements ConstraintValidator<NIK, String> {
    private boolean isRequired;

    @Override
    public void initialize(NIK constraintAnnotation) {
        isRequired = constraintAnnotation.isRequired();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        try {
            if (!isRequired && value.isEmpty()) {
                return true;
            }
            return value.length() == 16 && value.matches("\\d{16}");
        } catch (Exception ex) {
            return false;
        }
    }

}