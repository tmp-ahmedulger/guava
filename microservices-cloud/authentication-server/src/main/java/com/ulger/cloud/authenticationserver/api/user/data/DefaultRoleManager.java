package com.ulger.cloud.authenticationserver.api.user.data;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultRoleManager implements RoleManager {

    private final RoleRepository roleRepository;

    public DefaultRoleManager(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<RoleEntity> getById(long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void addRole(long id, String name) {
        roleRepository.save(new RoleEntity(id, name));
    }
}