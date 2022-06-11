package com.ulger.exception;

public class ApiException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiException(Integer code) {
        this.code = code;
    }

    public ApiException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ApiException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public ApiException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}