package com.ulger.guava.parceldeliveryservice.api.permission;

public interface PermissionChecker<T extends PermissionCheckParams> {

    PermissionResult check(T params);
}