package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission;

import com.ulger.guava.parceldeliveryservice.api.permission.PermissionCheckParams;
import lombok.Getter;

@Getter
public class AddressUpdatePermissionCheckParams implements PermissionCheckParams {

    private final Long ownerUserId;
    private final Long attemptingUserId;

    public AddressUpdatePermissionCheckParams(Long ownerUserId, Long attemptingUserId) {
        this.ownerUserId = ownerUserId;
        this.attemptingUserId = attemptingUserId;
    }
}