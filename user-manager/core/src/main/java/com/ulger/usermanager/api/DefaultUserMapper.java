package com.ulger.usermanager.api;

import java.util.stream.Collectors;

public class DefaultUserMapper implements UserMapper {

    @Override
    public User mapModificationData(UserModificationData modificationData) {

        MutableUser mutableUser = new MutableUser();

        mutableUser.setEmail(modificationData.getEmail());
        mutableUser.setCredential(modificationData.getHashPassword());
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