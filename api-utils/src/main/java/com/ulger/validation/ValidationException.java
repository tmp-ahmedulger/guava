package com.ulger.validation;

import java.util.Set;

public class ValidationException extends RuntimeException {

    private Set<String> errors;

    public Set<String> getErrors() {
        return errors;
    }

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidationException(Set<String> errors) {
        this.errors = errors;
    }

    public ValidationException(String message, Set<String> errors) {
        super(message);
        this.errors = errors;
    }

    public ValidationException(String message, Throwable cause, Set<String> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public ValidationException(Throwable cause, Set<String> errors) {
        super(cause);
        this.errors = errors;
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Set<String> errors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errors = errors;
    }
}