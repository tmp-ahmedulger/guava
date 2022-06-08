package com.ulger.usermanager.api.validation;

/**
 * Defines how to validate an email
 */
public interface EmailValidator {

    /**
     * @param email
     * @return true if valid or else false
     */
    boolean isValid(String email);
}