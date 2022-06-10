package com.ulger.guava.parceldeliveryservice.api.parcel.operation;

public class OperationPermissionException extends RuntimeException {

    private final Long attemptingUserId;

    public OperationPermissionException(Long attemptingUserId) {
        this.attemptingUserId = attemptingUserId;
    }

    public OperationPermissionException(String message, Long attemptingUserId) {
        super(message);
        this.attemptingUserId = attemptingUserId;
    }

    public OperationPermissionException(String message, Throwable cause, Long attemptingUserId) {
        super(message, cause);
        this.attemptingUserId = attemptingUserId;
    }

    public OperationPermissionException(Throwable cause, Long attemptingUserId) {
        super(cause);
        this.attemptingUserId = attemptingUserId;
    }

    public OperationPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Long attemptingUserId) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.attemptingUserId = attemptingUserId;
    }
}