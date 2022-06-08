package com.ulger.guava.parceldeliveryservice.api.parcel;

public interface Parcel {

    Long getId();

    String getBarcode();

    Long getUserId();

    long getWeightInGrams();

    String getDeliveryAddress();

    LoadingStatus getLoadingStatus();
}