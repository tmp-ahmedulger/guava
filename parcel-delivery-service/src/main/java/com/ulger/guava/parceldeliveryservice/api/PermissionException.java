package com.ulger.guava.parceldeliveryservice.api;

public class PermissionException extends RuntimeException {

    private Object params;

    public Object getParams() {
        return params;
    }

    public PermissionException() {
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PermissionException(Object params) {
        this.params = params;
    }

    public PermissionException(String message, Object params) {
        super(message);
        this.params = params;
    }

    public PermissionException(String message, Throwable cause, Object params) {
        super(message, cause);
        this.params = params;
    }

    public PermissionException(Throwable cause, Object params) {
        super(cause);
        this.params = params;
    }

    public PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object params) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.params = params;
    }
}