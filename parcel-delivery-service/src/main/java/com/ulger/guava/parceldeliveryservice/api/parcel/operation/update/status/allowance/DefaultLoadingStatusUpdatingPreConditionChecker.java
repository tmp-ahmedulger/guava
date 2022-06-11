package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.allowance;

import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.LoadingStatusUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.permission.LoadingStatusUpdatePermissionCheckParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.permission.LoadingStatusUpdatePermissionChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class DefaultLoadingStatusUpdatingPreConditionChecker implements LoadingStatusUpdatingPreConditionChecker {

    private final LoadingStatusUpdatePermissionChecker permissionChecker;

    public DefaultLoadingStatusUpdatingPreConditionChecker(LoadingStatusUpdatePermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }

    @Override
    public void check(Parcel existingParcel, LoadingStatusUpdateDto loadingStatusUpdateDto) {
        LoadingStatusUpdatePermissionCheckParams permissionCheckParams = new LoadingStatusUpdatePermissionCheckParams(
                loadingStatusUpdateDto.getUpdaterUserId(),
                existingParcel.getOwnerUserId());

        if (!permissionChecker.check(permissionCheckParams).isPermitted()) {
            log.warn("Illegal parcel update operation detected. Parcel with id '{}' is attempted to update by userId={}. Parcel owner userId is '{}'",
                    loadingStatusUpdateDto.getParcelId(),
                    loadingStatusUpdateDto.getUpdaterUserId(),
                    existingParcel.getOwnerUserId());

            throw new OperationPermissionException(permissionCheckParams);
        }

        if (Objects.equals(loadingStatusUpdateDto.getLoadingStatus(), existingParcel.getLoadingStatus())) {
            log.info("Loading Statuses are same, skipping update. sourceLoadingStatus={}, targetLoadingStatus={}",
                    existingParcel.getLoadingStatus(),
                    loadingStatusUpdateDto.getLoadingStatus());

            throw new ApiException(ApiErrorCode.SAME_LOADING_STATUS.getKey());
        }
    }
}