package com.ulger.usermanager.api.validation;

import com.ulger.usermanager.api.UserModificationData;
import com.ulger.validation.ValidationResult;

/**
 * This interface is a contract for what kind of validations to be applied
 */
public interface UserValidator {

    /**
     * Do validate customer input for creation and returns detail
     * information of validation result
     *
     * @param userModificationData
     * @return ValidationResult contains detailed validation result
     */
    ValidationResult validateToCreate(UserModificationData userModificationData);
}