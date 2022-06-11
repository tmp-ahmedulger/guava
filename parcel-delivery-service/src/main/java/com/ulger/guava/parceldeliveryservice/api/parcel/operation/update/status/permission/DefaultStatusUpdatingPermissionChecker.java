package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.permission;

import com.ulger.guava.parceldeliveryservice.api.PreDefinedRole;
import com.ulger.guava.parceldeliveryservice.api.permission.DefaultPermissionResult;
import com.ulger.guava.parceldeliveryservice.api.permission.PermissionResult;
import com.ulger.guava.parceldeliveryservice.api.user.UserLoader;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Qualifier("addressUpdatePermissionChecker")
public class DefaultStatusUpdatingPermissionChecker implements StatusUpdatingPermissionChecker {

    private final UserLoader userLoader;
    private final Set<String> permittedRoles;

    public DefaultStatusUpdatingPermissionChecker(@Qualifier("userLoaderProvider") UserLoader userLoader) {
        this.userLoader = userLoader;
        this.permittedRoles = loadPermittedRoles();
    }

    @Override
    public PermissionResult check(StatusUpdatingPermissionCheckParams params) {

        User user = userLoader.loadById(params.getAttemptingUserId());

        if (user == null) {
            return DefaultPermissionResult.buildUnPermitted();
        }

        boolean isPermitted = user.hasAnyRole(permittedRoles);

        return DefaultPermissionResult.of(isPermitted);
    }

    private Set<String> loadPermittedRoles() {
        return Set.of(
                PreDefinedRole.ADMIN.getRole().getName(),
                PreDefinedRole.COURIER.getRole().getName());
    }
}