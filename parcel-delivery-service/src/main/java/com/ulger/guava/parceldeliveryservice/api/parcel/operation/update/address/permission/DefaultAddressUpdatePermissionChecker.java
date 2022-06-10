package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission;

import com.ulger.guava.parceldeliveryservice.api.permission.DefaultPermissionResult;
import com.ulger.guava.parceldeliveryservice.api.permission.PermissionResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier("addressUpdatePermissionChecker")
public class DefaultAddressUpdatePermissionChecker implements AddressUpdatePermissionChecker {

    @Override
    public PermissionResult check(AddressUpdatePermissionCheckParams params) {
        if (Objects.equals(params.getOwnerUserId(), params.getAttemptingUserId())) {
            return new DefaultPermissionResult(true);
        }

        return new DefaultPermissionResult(false);
    }
}