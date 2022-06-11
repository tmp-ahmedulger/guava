package com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.update;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.ToString;

@ToString
public class ParcelStatusUpdateRequest {

    private String status;

    public String getStatus() {
        return status;
    }

    @JsonSetter("status")
    public void setStatus(String status) {
        this.status = status;
    }
}