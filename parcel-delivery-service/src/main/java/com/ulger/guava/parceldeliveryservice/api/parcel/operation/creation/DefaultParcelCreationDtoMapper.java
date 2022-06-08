package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DefaultParcelCreationDtoMapper extends ParcelCreationDtoMapper {

    @Override
    @Mapping(source = "parcelCreationDto.userId", target = "userId")
    @Mapping(source = "parcelCreationDto.weightInGrams", target = "weightInGrams")
    @Mapping(source = "parcelCreationDto.deliveryAddress", target = "deliveryAddress")
    Parcel map(ParcelCreationDto parcelCreationDto);
}