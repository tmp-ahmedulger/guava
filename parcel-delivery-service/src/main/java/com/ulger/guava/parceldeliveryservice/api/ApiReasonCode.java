package com.ulger.guava.parceldeliveryservice.api;

public enum ApiReasonCode {

    SAME_ADDRESS(1),
    PARCEL_NOT_FOUND(2);

    private final int code;

    ApiReasonCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}