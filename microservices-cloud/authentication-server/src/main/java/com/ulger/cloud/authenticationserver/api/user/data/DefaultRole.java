package com.ulger.cloud.authenticationserver.api.user.data;

public enum DefaultRole {

    STANDARD(1), ADMIN(2), COURIER(3);

    private final long id;

    DefaultRole(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}