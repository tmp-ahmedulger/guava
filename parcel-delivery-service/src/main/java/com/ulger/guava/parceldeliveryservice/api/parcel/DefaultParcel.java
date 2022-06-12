package com.ulger.guava.parceldeliveryservice.api.parcel;

import com.ulger.guava.parceldeliveryservice.api.courier.Courier;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultParcel implements Parcel {

    private Long id;
    private String barcode;
    private Long ownerUserId;
    private long weightInGrams;
    private String deliveryAddress;
    private Status status;
    private Courier courier;

}