package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.DefaultParcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DefaultParcelEntityMapper extends ParcelEntityMapper {

    @Override
    @Mapping(source = "parcel.id", target = "id")
    @Mapping(source = "parcel.barcode", target = "barcode")
    @Mapping(source = "parcel.weightInGrams", target = "weightInGrams")
    @Mapping(source = "parcel.deliveryAddress", target = "deliveryAddress")
    @Mapping(source = "parcel.status", target = "status")
    @Mapping(source = "parcel.state", target = "state")
    ParcelEntity mapToEntity(Parcel parcel);

    @Override
    @Mapping(source = "parcelEntity.id", target = "id")
    @Mapping(source = "parcelEntity.barcode", target = "barcode")
    @Mapping(source = "parcelEntity.weightInGrams", target = "weightInGrams")
    @Mapping(source = "parcelEntity.deliveryAddress", target = "deliveryAddress")
    @Mapping(source = "parcelEntity.status", target = "status")
    @Mapping(source = "parcelEntity.state", target = "state")
    DefaultParcel mapFromEntity(ParcelEntity parcelEntity);
}