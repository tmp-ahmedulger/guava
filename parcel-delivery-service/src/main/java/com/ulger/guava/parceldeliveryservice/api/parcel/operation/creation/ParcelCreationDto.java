package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelCreationDto {

    private Long userId;
    private long weight_in_grams;
    private String delivery_address;
}