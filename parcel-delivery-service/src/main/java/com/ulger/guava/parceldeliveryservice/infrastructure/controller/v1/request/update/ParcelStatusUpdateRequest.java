package com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.update;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.ToString;

@ToString
public class ParcelStatusUpdateRequest {

    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    @JsonSetter("statusCode")
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}