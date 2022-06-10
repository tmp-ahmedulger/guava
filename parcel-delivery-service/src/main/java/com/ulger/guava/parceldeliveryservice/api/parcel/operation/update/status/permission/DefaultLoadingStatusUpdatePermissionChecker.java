package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.permission;

import com.ulger.guava.parceldeliveryservice.api.permission.DefaultPermissionResult;
import com.ulger.guava.parceldeliveryservice.api.permission.PermissionResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier("addressUpdatePermissionChecker")
public class DefaultLoadingStatusUpdatePermissionChecker implements LoadingStatusUpdatePermissionChecker {

    @Override
    public PermissionResult check(LoadingStatusUpdatePermissionCheckParams params) {
        if (Objects.equals(params.getOwnerUserId(), params.getAttemptingUserId())) {
            return new DefaultPermissionResult(true);
        }

        return new DefaultPermissionResult(false);
    }
}