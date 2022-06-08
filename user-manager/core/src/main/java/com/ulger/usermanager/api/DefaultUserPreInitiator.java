package com.ulger.usermanager.api;

import java.util.stream.Collectors;

public class DefaultUserPreInitiator implements UserPreInitiator {

    private final CredentialEncoder credentialEncoder;

    public DefaultUserPreInitiator(CredentialEncoder credentialEncoder) {
        this.credentialEncoder = credentialEncoder;
    }

    @Override
    public User initiate(UserModificationData modificationData) {

        MutableUser mutableUser = new MutableUser();

        mutableUser.setEmail(modificationData.getEmail());
        mutableUser.setCredential(credentialEncoder.encode(modificationData.getRawPassword()));
        mutableUser.setDisplayName(modificationData.getDisplayName());
        mutableUser.setRoles(
                modificationData
                        .getRoleIds()
                        .stream()
                        .map(MutableRole::new)
                        .collect(Collectors.toSet())
        );

        return mutableUser;
    }
}