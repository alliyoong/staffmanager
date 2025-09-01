package com.webapp.staffmanager.util.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppNotBlankValidator implements ConstraintValidator<AppNotBlank, String> {

    @Override
    public void initialize(AppNotBlank arg0) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.trim().isEmpty();
    }

}
