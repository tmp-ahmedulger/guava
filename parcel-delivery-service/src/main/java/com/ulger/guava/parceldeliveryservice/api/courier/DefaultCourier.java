package com.ulger.guava.parceldeliveryservice.api.courier;

import lombok.Builder;

@Builder
public class DefaultCourier implements Courier {

    private Long id;
    private String displayName;
    private String phoneNumber;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
}