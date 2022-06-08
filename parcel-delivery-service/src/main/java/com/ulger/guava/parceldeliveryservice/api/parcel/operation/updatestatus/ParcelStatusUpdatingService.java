package com.ulger.guava.parceldeliveryservice.api.parcel.operation.updatestatus;

import com.ulger.guava.parceldeliveryservice.api.parcel.LoadingStatus;

/**
 * This interface defines update operations of parcels status.
 */
public interface ParcelStatusUpdatingService {

    /**
     * This method updated parcel loading status
     *
     * @param parcelId refers parcel identifier
     * @param targetStatus is status that parcel will be updated
     * @throws IllegalArgumentException when any of given ids is null
     */
    void update(Long parcelId, LoadingStatus targetStatus);
}