package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;

public interface ParcelCreationDtoMapper {

    Parcel map(ParcelCreationDto parcelCreationDto);
}