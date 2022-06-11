package com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.update;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.ToString;

@ToString
public class ParcelLoadingStatusUpdateRequest {

    private String loadingStatus;

    public String getLoadingStatus() {
        return loadingStatus;
    }

    @JsonSetter("loadingStatus")
    public void setDeliveryAddress(String loadingStatus) {
        this.loadingStatus = loadingStatus;
    }
}