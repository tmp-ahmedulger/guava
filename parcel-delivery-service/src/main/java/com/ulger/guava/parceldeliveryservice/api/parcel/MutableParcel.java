package com.ulger.guava.parceldeliveryservice.api.parcel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MutableParcel implements Parcel {

    private Long id;
    private String barcode;
    private Long userId;
    private long weightInGrams;
    private String deliveryAddress;
    private LoadingStatus loadingStatus;

}