package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.allowance;

import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.permission.StatusUpdatingPermissionCheckParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.permission.StatusUpdatingPermissionChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultStatusUpdatingPreConditionChecker implements StatusUpdatingPreConditionChecker {

    private final StatusUpdatingPermissionChecker permissionChecker;

    public DefaultStatusUpdatingPreConditionChecker(StatusUpdatingPermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }

    @Override
    public void check(Parcel existingParcel, StatusUpdateDto statusUpdateDto) {
        StatusUpdatingPermissionCheckParams permissionCheckParams = new StatusUpdatingPermissionCheckParams(
                statusUpdateDto.getUpdaterUserId(),
                existingParcel.getOwnerUserId());

        if (!permissionChecker.check(permissionCheckParams).isPermitted()) {
            log.warn("Illegal parcel update operation detected. Parcel with id '{}' is attempted to update by userId={}. Parcel owner userId is '{}'",
                    statusUpdateDto.getParcelId(),
                    statusUpdateDto.getUpdaterUserId(),
                    existingParcel.getOwnerUserId());

            throw new OperationPermissionException(permissionCheckParams);
        }

        if (statusUpdateDto.getStatus() == existingParcel.getStatus()) {
            log.info("Statuses are same, skipping update. sourceLStatus={}, targetStatus={}",
                    existingParcel.getStatus(),
                    statusUpdateDto.getStatusCode());

            throw new ApiException(ApiErrorCode.SAME_STATUS.getKey());
        }
    }
}