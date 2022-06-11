package com.ulger.guava.parceldeliveryservice.api;

import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.PDRole;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.Role;

public enum PreDefinedRole {

    STANDARD(new PDRole("STANDARD")),
    ADMIN(new PDRole("ADMIN")),
    COURIER(new PDRole("COURIER"));

    private final Role role;

    PreDefinedRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}