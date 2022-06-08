package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.LoadingStatus;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.ParcelOperationException;

/**
 * This interface defines creation operations of parcels.
 */
public interface ParcelCreationManager {

    /**
     * Creates a parcel with given data. Created parcel must be in Created status when it is created
     * @see LoadingStatus
     *
     * @param parcelCreationDto holds creation info
     * @throws com.ulger.validation.ValidationException when given creation data is invalid
     * @throws ParcelOperationException when creation failed
     * @return created parcel
     */
    Parcel create(ParcelCreationDto parcelCreationDto);
}