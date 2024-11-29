package com.jobseeker.hrms.candidate.data.validator.education.major;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.jobseeker.master.education.EducationLevel;
import org.jobseeker.master.repositories.education.EducationLevelMasterRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class MajorValidator implements ConstraintValidator<MajorValidation, Object> {

    private String checkBy;
    private String target;

    @Override
    public void initialize(MajorValidation constraintAnnotation) {
        this.checkBy = constraintAnnotation.checkBy();
        this.target = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {

            // Use reflection to access fields from the target object
            final Object checkByValue = BeanUtils.getProperty(object, checkBy);
            final Object targetValue = BeanUtils.getProperty(object, target);

            // Check validation logic
            if (targetValue != null && Integer.parseInt(checkByValue.toString()) > 2) {
                return true;
            }

            return targetValue != null;

        } catch (Exception e) {
            return false;
        }
    }
}
