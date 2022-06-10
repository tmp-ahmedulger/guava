package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

/**
 * This interface defines update operations of parcels.
 */
public interface AddressUpdateService {

    /**
     * This method updates parcel delivery address
     *
     * @param addressUpdateDto is information that will be used for status updating
     * @throws com.ulger.validation.ValidationException if given parameters are invalid
     *
     * @throws com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException
     * if parcel doesn't belong to user with given userId
     *
     * @throws com.ulger.guava.parceldeliveryservice.api.ApiException with key 'error.parcel.address.same' if
     * given delivery address is same with current address
     *
     * @throws com.ulger.guava.parceldeliveryservice.api.ApiException with key 'error.parcel.not-found' if parcel not found
     * @return true if update is successful or else false
     */
    boolean update(AddressUpdateDto addressUpdateDto);
}