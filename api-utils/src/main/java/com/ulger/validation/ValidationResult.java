package com.ulger.validation;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ValidationResult {

    private String message;
    private Set<String> errors;
    private Map<Integer, String> errorsByCode;

    public ValidationResult() {
        this.errors = new LinkedHashSet<>();
        this.errorsByCode = new LinkedHashMap<>();
    }

    public boolean hasError() {
        return errors != null && !errors.isEmpty();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public Map<Integer, String> getErrorsByCode() {
        return errorsByCode;
    }

    public void addErrorByCode(Integer code, String error) {
        this.errorsByCode.put(code, error);
    }
}