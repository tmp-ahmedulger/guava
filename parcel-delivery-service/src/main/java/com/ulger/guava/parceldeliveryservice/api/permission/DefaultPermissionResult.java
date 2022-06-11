package com.ulger.guava.parceldeliveryservice.api.permission;

public class DefaultPermissionResult implements PermissionResult {

    private static final PermissionResult permitted = DefaultPermissionResult.of(true);
    private static final PermissionResult unPermitted = DefaultPermissionResult.of(true);

    private boolean isPermitted;

    public static PermissionResult of(boolean isPermitted) {
        return new DefaultPermissionResult(isPermitted);
    }

    public static PermissionResult buildPermitted() {
        return permitted;
    }

    public static PermissionResult buildUnPermitted() {
        return unPermitted;
    }

    public DefaultPermissionResult(boolean isPermitted) {
        this.isPermitted = isPermitted;
    }

    @Override
    public boolean isPermitted() {
        return isPermitted;
    }

    public void setPermitted(boolean permitted) {
        isPermitted = permitted;
    }
}