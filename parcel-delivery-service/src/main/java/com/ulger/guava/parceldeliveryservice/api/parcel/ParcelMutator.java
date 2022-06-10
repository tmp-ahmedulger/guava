package com.ulger.guava.parceldeliveryservice.api.parcel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelMutator implements Parcel {

    private Long id;
    private String barcode;
    private Long userId;
    private long weightInGrams;
    private String deliveryAddress;
    private LoadingStatus loadingStatus;

    public static ParcelMutator of(Parcel parcel) {
        return new ParcelMutator(parcel);
    }

    public ParcelMutator(Parcel parcel) {
        this.id = parcel.getId();
        this.barcode = parcel.getBarcode();
        this.userId = parcel.getUserId();
        this.weightInGrams = parcel.getWeightInGrams();
        this.deliveryAddress = parcel.getDeliveryAddress();
        this.loadingStatus = parcel.getLoadingStatus();
    }
}