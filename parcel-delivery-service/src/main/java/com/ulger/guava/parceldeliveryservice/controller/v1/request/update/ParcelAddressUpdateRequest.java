package com.ulger.guava.parceldeliveryservice.controller.v1.request.update;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.ToString;

@ToString
public class ParcelAddressUpdateRequest {

    private String deliveryAddress;

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @JsonSetter("deliveryAddress")
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}