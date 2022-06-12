package com.ulger.guava.parceldeliveryservice.api;

public enum ApiErrorCode {

    SAME_ADDRESS(1, "error.parcel.address.same"),
    PARCEL_NOT_FOUND(2, "error.parcel.not-found"),
    SAME_STATUS(3, "error.parcel.status.same"),
    ILLEGAL_ATTEMPT(4, "error.operation.illegal.attempt"),
    CANCELLED_UPDATE_ATTEMPT(5, "error.operation.cancelled.update.attempt");

    private final int code;
    private final String key;

    ApiErrorCode(int code, String key) {
        this.code = code;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }
}