package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ParcelCreationDto {

    private Long userId;
    private Long weightInGrams;
    private String deliveryAddress;
}