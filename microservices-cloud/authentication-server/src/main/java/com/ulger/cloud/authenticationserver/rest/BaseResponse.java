package com.ulger.cloud.authenticationserver.rest;

import lombok.Builder;

import java.util.Collection;

@Builder
public class BaseResponse {

    private String message;
    private Collection<String> errors;

    public BaseResponse(String message) {
        this.message = message;
    }

    public BaseResponse(Collection<String> errors) {
        this.errors = errors;
    }

    public BaseResponse(String message, Collection<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<String> getErrors() {
        return errors;
    }

    public void setErrors(Collection<String> errors) {
        this.errors = errors;
    }
}