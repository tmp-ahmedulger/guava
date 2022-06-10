package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status;

public interface ParcelLoadingStatusUpdateService {

    /**
     * This method updated parcel loading status
     *
     * @param parcelId refers parcel identifier
     * @param targetStatus is status that parcel will be updated
     * @throws com.ulger.validation.ValidationException if given parameters are invalid
     * @throws IllegalArgumentException when any of given ids is null
     * @throws com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException
     * if parcel doesn't belong to user with given userId
     * @throws com.ulger.exception.ApiException with code "1" if given targetStatus is same with current status
     */
    void update(Long parcelId, Long userId, String targetStatus);
}