package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.permission;

import com.ulger.guava.parceldeliveryservice.api.permission.PermissionCheckParams;
import lombok.Getter;

@Getter
public class StatusUpdatingPermissionCheckParams implements PermissionCheckParams {

    private final Long ownerUserId;
    private final Long attemptingUserId;

    public StatusUpdatingPermissionCheckParams(Long ownerUserId, Long attemptingUserId) {
        this.ownerUserId = ownerUserId;
        this.attemptingUserId = attemptingUserId;
    }
}