package com.ulger.guava.parceldeliveryservice.api.parcel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultParcel implements Parcel {

    private Long id;
    private String barcode;
    private Long userId;
    private long weightInGrams;
    private String deliveryAddress;
    private LoadingStatus loadingStatus;

}