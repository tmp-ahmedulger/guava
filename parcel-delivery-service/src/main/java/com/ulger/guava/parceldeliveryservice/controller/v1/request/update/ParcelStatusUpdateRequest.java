package com.ulger.guava.parceldeliveryservice.controller.v1.request.update;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.ToString;

@ToString
public class ParcelStatusUpdateRequest {

    private String loadingStatus;

    public String getLoadingStatus() {
        return loadingStatus;
    }

    @JsonSetter("loadingStatus")
    public void setDeliveryAddress(String loadingStatus) {
        this.loadingStatus = loadingStatus;
    }
}