package com.ulger.guava.parceldeliveryservice.api.parcel;

public interface Parcel {

    Long getId();

    Long getUserId();

    long getWeightInGrams();

    String getDeliveryAddress();
}