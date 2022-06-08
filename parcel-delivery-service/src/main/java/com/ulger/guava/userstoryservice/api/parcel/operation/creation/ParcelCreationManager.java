package com.ulger.guava.userstoryservice.api.parcel.operation.creation;

import com.ulger.guava.userstoryservice.api.parcel.Parcel;

/**
 * This interface defines creation operations of parcels.
 */
public interface ParcelCreationManager {

    /**
     *
     * @param parcelCreationDto holds creation info
     * @throws com.ulger.validation.ValidationException when given creation data is invalid
     * @throws com.ulger.guava.userstoryservice.api.parcel.ParcelOperationException when creation failed
     * @return created parcel
     */
    Parcel create(ParcelCreationDto parcelCreationDto);
}