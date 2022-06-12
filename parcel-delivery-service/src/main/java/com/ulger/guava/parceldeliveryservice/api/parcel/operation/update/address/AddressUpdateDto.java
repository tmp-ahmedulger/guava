package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUpdateDto {

    private Long parcelId;
    private Long updaterUserId;
    private String deliveryAddress;

    private AddressUpdateDto(Long parcelId) {
        this.parcelId = parcelId;
    }

    public static AddressUpdateDto buildWithParcelId(Long parcelId) {
        return new AddressUpdateDto(parcelId);
    }
}