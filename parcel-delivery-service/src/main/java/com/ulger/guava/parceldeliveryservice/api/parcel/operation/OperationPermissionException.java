package com.ulger.guava.parceldeliveryservice.api.parcel.operation;

import com.ulger.guava.parceldeliveryservice.api.permission.PermissionCheckParams;

public class OperationPermissionException extends RuntimeException {

    private final PermissionCheckParams permissionCheckParams;

    public OperationPermissionException(PermissionCheckParams permissionCheckParams) {
        this.permissionCheckParams = permissionCheckParams;
    }

    public OperationPermissionException(String message, PermissionCheckParams permissionCheckParams) {
        super(message);
        this.permissionCheckParams = permissionCheckParams;
    }

    public OperationPermissionException(String message, Throwable cause, PermissionCheckParams permissionCheckParams) {
        super(message, cause);
        this.permissionCheckParams = permissionCheckParams;
    }

    public OperationPermissionException(Throwable cause, PermissionCheckParams permissionCheckParams) {
        super(cause);
        this.permissionCheckParams = permissionCheckParams;
    }

    public OperationPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, PermissionCheckParams permissionCheckParams) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.permissionCheckParams = permissionCheckParams;
    }
}