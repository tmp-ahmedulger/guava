package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;

/**
 * This interface is responsible with pre-checking whether parcel status can be updated
 */
public interface StatusUpdateConsentChecker {

    /**
     *  Checks if conditions meet if update can bo done or not
     *
     * @param existingParcel current parcel data
     * @param statusUpdateDto status data that parcel will being updated with
     * @throws IllegalArgumentException when given data is null
     * @throws com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException
     *          when update does not fit for conditions
     */
    void check(Parcel existingParcel, StatusUpdateDto statusUpdateDto);
}