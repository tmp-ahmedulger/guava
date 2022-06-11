package com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;

public interface ParcelCreationRequestMapper {

    ParcelCreationDto map(ParcelCreationRequest parcelCreationRequest, Long userId);
}
