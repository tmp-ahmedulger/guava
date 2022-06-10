package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.allowance;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;

/**
 * This interface is responsible with pre-checking whether parcel can be updated
 */
public interface AddressUpdatingPreConditionChecker {

    /**
     *  Checks if conditions meet if update can bo done or not
     *
     * @param existingParcel current parcel data
     * @param addressUpdateDto address data that parcel will being updated with
     * @throws IllegalArgumentException when given data is null
     * @throws com.ulger.guava.parceldeliveryservice.api.parcel.operation.PreConditionException when
     * update does not fit for conditions
     */
    void check(Parcel existingParcel, AddressUpdateDto addressUpdateDto);
}