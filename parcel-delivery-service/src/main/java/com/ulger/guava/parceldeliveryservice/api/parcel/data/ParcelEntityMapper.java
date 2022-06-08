package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;

public interface ParcelEntityMapper {

    ParcelEntity map(Parcel parcel);

    Parcel map(ParcelEntity parcelEntity);
}
