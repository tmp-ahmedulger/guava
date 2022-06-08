package com.ulger.usermanager.api.validation;

public class ApacheEmailValidator implements EmailValidator {

    private org.apache.commons.validator.routines.EmailValidator apacheEmailValidator = org.apache.commons.validator.routines.EmailValidator.getInstance();

    @Override
    public boolean isValid(String email) {
        return apacheEmailValidator.isValid(email);
    }
}