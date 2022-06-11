package com.ulger.guava.parceldeliveryservice.api.parcel;

public interface Parcel {

    Long getId();

    String getBarcode();

    Long getOwnerUserId();

    long getWeightInGrams();

    String getDeliveryAddress();

    Status getStatus();
}