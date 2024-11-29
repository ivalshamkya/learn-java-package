package com.jobseeker.hrms.candidate.data.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class FieldMustMatchValidator implements ConstraintValidator<FieldMustMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMustMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext constraintValidatorContext) {
        try {
            final Object firstObj = BeanUtils.getProperty(object, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(object, secondFieldName);

            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {
            return false;
        }
    }
}
