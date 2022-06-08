package com.ulger.guava.parceldeliveryservice.controller.v1.request.creation;


import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.ToString;

@ToString
public class ParcelCreationRequest {

    private long weightInGrams;
    private String deliveryAddress;

    public long getWeightInGrams() {
        return weightInGrams;
    }

    @JsonSetter("weightInGrams")
    public void setWeightInGrams(long weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @JsonSetter("deliveryAddress")
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}