package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParcelAddressUpdateDto {

    private Long parcelId;
    private Long userId;
    private String deliveryAddress;
}