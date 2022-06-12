package com.ulger.guava.parceldeliveryservice.api.parcel;

public interface ParcelOperationListener {

    void onOperate(String operationType, Object params);
}