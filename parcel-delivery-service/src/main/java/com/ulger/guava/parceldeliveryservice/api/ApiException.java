package com.ulger.guava.parceldeliveryservice.api;

public class ApiException extends RuntimeException {

    private final String key;
    private final Object[] args;

    public ApiException(String key) {
        this.key = key;
        this.args = null;
    }

    public ApiException(String key, Object...args) {
        this.key = key;
        this.args = args;
    }

    public String getKey() {
        return key;
    }

    public Object[] getArgs() {
        return args;
    }
}