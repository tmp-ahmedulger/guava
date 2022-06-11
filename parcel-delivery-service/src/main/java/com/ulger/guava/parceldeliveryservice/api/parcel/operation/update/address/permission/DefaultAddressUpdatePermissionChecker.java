package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission;

import com.ulger.guava.parceldeliveryservice.api.PreDefinedRole;
import com.ulger.guava.parceldeliveryservice.api.permission.DefaultPermissionResult;
import com.ulger.guava.parceldeliveryservice.api.permission.PermissionResult;
import com.ulger.guava.parceldeliveryservice.api.user.UserLoader;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier("addressUpdatePermissionChecker")
public class DefaultAddressUpdatePermissionChecker implements AddressUpdatePermissionChecker {

    private final UserLoader userLoader;

    public DefaultAddressUpdatePermissionChecker(@Qualifier("userLoaderProvider") UserLoader userLoader) {
        this.userLoader = userLoader;
    }

    @Override
    public PermissionResult check(AddressUpdatePermissionCheckParams params) {

        if (Objects.equals(params.getOwnerUserId(), params.getAttemptingUserId())) {
            return DefaultPermissionResult.buildPermitted();
        }

        User user = userLoader.loadById(params.getAttemptingUserId());

        if (user != null && user.hasRole(PreDefinedRole.ADMIN.getRole().getName())) {
            return DefaultPermissionResult.buildPermitted();
        }

        return DefaultPermissionResult.buildUnPermitted();
    }
}