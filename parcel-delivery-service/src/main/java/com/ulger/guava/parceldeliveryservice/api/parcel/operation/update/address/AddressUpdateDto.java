package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressUpdateDto {

    private Long parcelId;
    private Long updaterUserId;
    private String deliveryAddress;
}