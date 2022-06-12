package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter;

import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.api.PermissionException;
import com.ulger.guava.parceldeliveryservice.api.PreDefinedRole;
import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException;
import com.ulger.guava.parceldeliveryservice.api.user.UserLoader;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier("addressUpdatePermissionConsentFilter")
public class AddressUpdatePermissionConsentFilter implements AddressUpdateConsentFilter {

    private final UserLoader userLoader;

    public AddressUpdatePermissionConsentFilter(UserLoader userLoader) {
        this.userLoader = userLoader;
    }

    @Override
    public void check(AddressUpdateConsentFilterParams params) {
        Long ownerUserId = params.getParcel().getOwnerUserId();
        Long updaterUserId = params.getAddressUpdateDto().getUpdaterUserId();

        if (Objects.equals(ownerUserId, updaterUserId)) {
            throw new ConsentFilterException(new ApiException(ApiErrorCode.SAME_ADDRESS.getKey()));
        }

        User user = userLoader.loadById(updaterUserId);

        if (user == null || !user.hasRole(PreDefinedRole.ADMIN.getRole().getName())) {
            throw new ConsentFilterException(new PermissionException());
        }
    }
}