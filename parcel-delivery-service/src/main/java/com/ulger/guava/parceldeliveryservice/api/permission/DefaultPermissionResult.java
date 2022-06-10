package com.ulger.guava.parceldeliveryservice.api.permission;

public class DefaultPermissionResult implements PermissionResult {

    private boolean isPermitted;

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