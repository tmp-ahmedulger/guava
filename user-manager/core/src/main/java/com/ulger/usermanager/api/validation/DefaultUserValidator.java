package com.ulger.usermanager.api.validation;

import com.ulger.usermanager.api.UserModificationData;
import com.ulger.validation.ValidationResult;
import org.apache.commons.lang3.StringUtils;

public class DefaultUserValidator implements UserValidator {

    private EmailValidator EmailValidator;

    public DefaultUserValidator(EmailValidator EmailValidator) {
        this.EmailValidator = EmailValidator;
    }

    @Override
    public ValidationResult validateToCreate(UserModificationData userModificationData) {
        return ValidationHelper
                .newInstance(this.EmailValidator, userModificationData)
                .validate()
                .getValidationResult();
    }

    private static class ValidationHelper {

        private EmailValidator EmailValidator;
        private UserModificationData modificationData;
        private ValidationResult validationResult;

        static ValidationHelper newInstance(EmailValidator EmailValidator, UserModificationData userModificationData) {
            return new ValidationHelper(EmailValidator, userModificationData);
        }

        public ValidationHelper(EmailValidator EmailValidator, UserModificationData modificationData) {
            this.EmailValidator = EmailValidator;
            this.modificationData = modificationData;
            this.validationResult = new ValidationResult();
        }

        ValidationHelper validate() {
            if (modificationData == null) {
                validationResult.addError("Creation data must not blank");
                return this;
            }

            if (StringUtils.isBlank(modificationData.getDisplayName())) {
                validationResult.addError("Display name is required");
            }

            if (StringUtils.isBlank(modificationData.getEmail())) {
                validationResult.addError("Email is required");
            } else if (!EmailValidator.isValid(modificationData.getEmail())) {
                validationResult.addError("Given email is not valid");
            }

            if (StringUtils.isBlank(modificationData.getPassword())) {
                validationResult.addError("Password is required");
            }

            return this;
        }

        ValidationResult getValidationResult() {
            return validationResult;
        }
    }
}