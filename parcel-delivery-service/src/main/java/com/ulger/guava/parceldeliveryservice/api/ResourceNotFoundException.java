package com.ulger.guava.parceldeliveryservice.api;

public class ResourceNotFoundException extends RuntimeException {

    private String key;
    private Object[] args;

    public String getKey() {
        return key;
    }

    public Object[] getArgs() {
        return args;
    }

    public ResourceNotFoundException(String key, Object...args) {
        this.key = key;
        this.args = args;
    }

    public ResourceNotFoundException(String message, String key, Object...args) {
        super(message);
        this.key = key;
        this.args = args;
    }

    public ResourceNotFoundException(String message, Throwable cause, String key, Object...args) {
        super(message, cause);
        this.key = key;
        this.args = args;
    }

    public ResourceNotFoundException(Throwable cause, String key, Object...args) {
        super(cause);
        this.key = key;
        this.args = args;
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String key, Object...args) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.key = key;
        this.args = args;
    }
}