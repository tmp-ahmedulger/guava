package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.ParcelOperationException;
import com.ulger.guava.parceldeliveryservice.api.parcel.Status;

/**
 * This interface defines creation operations of parcels.
 */
public interface ParcelCreationService {

    /**
     * Creates a parcel with given data. Created parcel must be in Created status when it is created
     * @see Status
     *
     * @param parcelCreationDto holds creation info
     * @throws com.ulger.validation.ValidationException when given creation data is invalid
     * @throws ParcelOperationException when creation failed
     * @return created parcel
     */
    Parcel create(ParcelCreationDto parcelCreationDto);
}