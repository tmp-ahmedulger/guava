package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;

public interface ParcelEntityMapper {

    ParcelEntity mapToEntity(Parcel parcel);

    Parcel mapFromEntity(ParcelEntity parcelEntity);
}
