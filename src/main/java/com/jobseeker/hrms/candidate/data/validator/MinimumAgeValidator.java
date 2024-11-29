package com.jobseeker.hrms.candidate.data.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class MinimumAgeValidator implements ConstraintValidator<MinimumAge, String> {
    private int minAge;

    @Override
    public void initialize(MinimumAge constraintAnnotation) {
        minAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        try {
            String dateStr = value.substring(0, 10);
            LocalDate dateOfBirth = LocalDate.parse(dateStr);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(dateOfBirth, currentDate).getYears();

            return age >= minAge;
        } catch (Exception ex) {
            return false;
        }
    }

}