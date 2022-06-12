package com.ulger.guava.parceldeliveryservice.api.consent;

public class ConsentFilterException extends RuntimeException {

    public ConsentFilterException() {
    }

    public ConsentFilterException(String message) {
        super(message);
    }

    public ConsentFilterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsentFilterException(Throwable cause) {
        super(cause);
    }

    public ConsentFilterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}