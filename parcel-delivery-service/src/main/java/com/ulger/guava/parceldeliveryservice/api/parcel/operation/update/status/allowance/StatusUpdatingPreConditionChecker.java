package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.allowance;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;

/**
 * This interface is responsible with pre-checking whether parcel can be updated
 */
public interface StatusUpdatingPreConditionChecker {

    /**
     *  Checks if conditions meet if update can be done or not
     *
     * @param existingParcel current parcel data
     * @param statusUpdateDto data that parcel will being updated with
     * @throws IllegalArgumentException when given data is null
     * @throws com.ulger.guava.parceldeliveryservice.api.parcel.operation.PreConditionException when
     * update does not fit for conditions
     */
    void check(Parcel existingParcel, StatusUpdateDto statusUpdateDto);
}