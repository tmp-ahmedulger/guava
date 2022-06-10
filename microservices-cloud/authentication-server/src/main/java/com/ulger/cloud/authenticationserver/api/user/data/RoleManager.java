package com.ulger.cloud.authenticationserver.api.user.data;

import java.util.Optional;

public interface RoleManager {

    Optional<RoleEntity> getById(long id);

    void addRole(long id, String name);
}