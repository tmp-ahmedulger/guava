package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status;

import com.ulger.guava.parceldeliveryservice.api.ResourceNotFoundException;

public interface StatusUpdateService {

    /**
     * This method updated parcel status
     *
     * @param statusUpdateDto is status data that parcel will be updated
     * @throws com.ulger.validation.ValidationException if given parameters are invalid
     *
     * @throws com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException
     *          if parcel doesn't belong to user with given userId
     *
     * @throws com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException
     *          if given targetStatus is same with current status
     *
     * @throws ResourceNotFoundException if parcel not found
     * @return true if update is successful or else false
     */
    boolean update(StatusUpdateDto statusUpdateDto);
}