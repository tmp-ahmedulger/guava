package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter;

import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;
import lombok.Getter;

@Getter
public class StatusUpdateConsentFilterParams implements ConsentFilterParams {

    private final Parcel parcel;
    private final StatusUpdateDto statusUpdateDto;

    public StatusUpdateConsentFilterParams(Parcel parcel, StatusUpdateDto statusUpdateDto) {
        this.parcel = parcel;
        this.statusUpdateDto = statusUpdateDto;
    }
}