package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.DefaultParcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DefaultParcelCreationDtoMapper extends ParcelCreationDtoMapper {

    @Override
    @Mapping(source = "parcelCreationDto.ownerUserId", target = "ownerUserId")
    @Mapping(source = "parcelCreationDto.weightInGrams", target = "weightInGrams")
    @Mapping(source = "parcelCreationDto.deliveryAddress", target = "deliveryAddress")
    DefaultParcel map(ParcelCreationDto parcelCreationDto);
}