package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;

import java.util.Optional;

public interface ParcelManager {

    Optional<Parcel> findById(Long id);

    Parcel save(Parcel parcel);

    void updateDeliveryAddress(long parcelId, String deliveryAddress);
}