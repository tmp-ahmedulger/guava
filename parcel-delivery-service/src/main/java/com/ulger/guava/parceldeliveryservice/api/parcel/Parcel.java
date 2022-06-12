package com.ulger.guava.parceldeliveryservice.api.parcel;

import com.ulger.guava.parceldeliveryservice.api.courier.Courier;

public interface Parcel {

    Long getId();

    String getBarcode();

    Long getOwnerUserId();

    long getWeightInGrams();

    String getDeliveryAddress();

    Status getStatus();

    State getState();

    Courier getCourier();
}