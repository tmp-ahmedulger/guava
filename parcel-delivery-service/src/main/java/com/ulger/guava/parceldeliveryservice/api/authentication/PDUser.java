package com.ulger.guava.parceldeliveryservice.api.authentication;

import com.ulger.usermanager.api.Role;
import com.ulger.usermanager.api.User;
import lombok.Builder;

import java.util.Set;

@Builder
public class PDUser implements User {

    private Long id;
    private String email;
    private String displayName;
    private String credential;
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
    public String getCredential() {
        return credential;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }
}