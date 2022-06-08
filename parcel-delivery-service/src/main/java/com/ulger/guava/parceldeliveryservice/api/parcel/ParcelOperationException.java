package com.ulger.guava.parceldeliveryservice.api.parcel;

/**
 * Keeps the parcel operation diagnose data.
 */
public class ParcelOperationException extends RuntimeException {

    private int reason;

    public int getReason() {
        return reason;
    }

    public ParcelOperationException(int reason) {
        this.reason = reason;
    }

    public ParcelOperationException(String message, int reason) {
        super(message);
        this.reason = reason;
    }

    public ParcelOperationException(String message, Throwable cause, int reason) {
        super(message, cause);
        this.reason = reason;
    }

    public ParcelOperationException(Throwable cause, int reason) {
        super(cause);
        this.reason = reason;
    }

    public ParcelOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int reason) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.reason = reason;
    }
}