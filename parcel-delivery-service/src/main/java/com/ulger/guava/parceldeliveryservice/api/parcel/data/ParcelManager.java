package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.Status;

import java.util.Optional;

public interface ParcelManager {

    Optional<Parcel> findById(Long id);

    Parcel save(Parcel parcel);

    boolean updateDeliveryAddress(long parcelId, String deliveryAddress);

    boolean updateStatus(long parcelId, Status status);
}