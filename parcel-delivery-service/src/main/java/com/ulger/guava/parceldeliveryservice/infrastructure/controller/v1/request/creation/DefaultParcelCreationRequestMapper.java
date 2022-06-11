package com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DefaultParcelCreationRequestMapper extends ParcelCreationRequestMapper {

    @Override
    @Mapping(source = "ownerUserId", target = "ownerUserId")
    @Mapping(source = "parcelCreationRequest.weightInGrams", target = "weightInGrams")
    @Mapping(source = "parcelCreationRequest.deliveryAddress", target = "deliveryAddress")
    ParcelCreationDto map(ParcelCreationRequest parcelCreationRequest, Long ownerUserId);
}