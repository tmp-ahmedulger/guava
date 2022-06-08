package com.ulger.guava.userstoryservice.api.parcel.operation.assign;

/**
 * This interfaces keeps the assignment operations.
 * Through assignment, each parcel will have a courier who will deliver the parcel.
 */
public interface ParcelAssignmentManager {

    /**
     * Assigns given parcel to courier to deliver.
     *
     * @param parcelId parcelId
     * @param courierId courierId
     * @throws IllegalArgumentException when parcelId is blank
     * @throws IllegalArgumentException when courierId is blank
     */
    void assign(Long parcelId, Long courierId);
}