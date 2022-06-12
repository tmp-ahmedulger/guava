package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter;

import com.ulger.guava.parceldeliveryservice.api.PermissionException;
import com.ulger.guava.parceldeliveryservice.api.PreDefinedRole;
import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException;
import com.ulger.guava.parceldeliveryservice.api.user.UserLoader;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Qualifier("statusUpdatePermissionConsentFilter")
public class StatusUpdatePermissionConsentFilter implements StatusUpdateConsentFilter {

    private final UserLoader userLoader;
    private final Set<String> permittedRoles;

    public StatusUpdatePermissionConsentFilter(UserLoader userLoader) {
        this.userLoader = userLoader;
        this.permittedRoles = loadPermittedRoles();
    }

    @Override
    public void check(StatusUpdateConsentFilterParams params) {
        Long updaterUserId = params.getStatusUpdateDto().getUpdaterUserId();

        User user = userLoader.loadById(updaterUserId);

        if (user == null || !user.hasAnyRole(permittedRoles)) {
            throw new ConsentFilterException(new PermissionException());
        }
    }

    private Set<String> loadPermittedRoles() {
        return Set.of(
                PreDefinedRole.ADMIN.getRole().getName(),
                PreDefinedRole.COURIER.getRole().getName());
    }
}