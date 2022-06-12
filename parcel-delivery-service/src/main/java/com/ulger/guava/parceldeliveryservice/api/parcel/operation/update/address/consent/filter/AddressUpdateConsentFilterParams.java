package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter;

import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import lombok.Getter;

@Getter
public class AddressUpdateConsentFilterParams implements ConsentFilterParams {

    private final Parcel parcel;
    private final AddressUpdateDto addressUpdateDto;

    public AddressUpdateConsentFilterParams(Parcel parcel, AddressUpdateDto addressUpdateDto) {
        this.parcel = parcel;
        this.addressUpdateDto = addressUpdateDto;
    }
}