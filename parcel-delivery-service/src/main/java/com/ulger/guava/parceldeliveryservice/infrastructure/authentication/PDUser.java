package com.ulger.guava.parceldeliveryservice.infrastructure.authentication;

import lombok.Builder;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Builder
public class PDUser implements User {

    private Long id;
    private String email;
    private String displayName;
    private Set<Role> roles;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean hasRole(String roleName) {
        return Optional
                .ofNullable(roles)
                .orElse(Collections.emptySet())
                .stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }
}