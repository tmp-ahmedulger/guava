package com.ulger.guava.parceldeliveryservice.api.parcel;

import com.ulger.guava.parceldeliveryservice.api.courier.Courier;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParcelMutator implements Parcel {

    private Long id;
    private String barcode;
    private Long ownerUserId;
    private long weightInGrams;
    private String deliveryAddress;
    private Status status;
    private Courier courier;

    public static ParcelMutator of(Parcel parcel) {
        return new ParcelMutator(parcel);
    }

    public ParcelMutator(Parcel parcel) {
        this.id = parcel.getId();
        this.barcode = parcel.getBarcode();
        this.ownerUserId = parcel.getOwnerUserId();
        this.weightInGrams = parcel.getWeightInGrams();
        this.deliveryAddress = parcel.getDeliveryAddress();
        this.status = parcel.getStatus();
        this.courier = parcel.getCourier();
    }
}