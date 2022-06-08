package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParcelCreationDto {

    private Long userId;
    private long weightInGrams;
    private String deliveryAddress;
}