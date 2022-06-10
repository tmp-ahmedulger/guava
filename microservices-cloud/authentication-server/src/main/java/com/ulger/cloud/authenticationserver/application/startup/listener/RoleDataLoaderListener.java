package com.ulger.cloud.authenticationserver.application.startup.listener;

import com.ulger.cloud.authenticationserver.api.user.data.DefaultRole;
import com.ulger.cloud.authenticationserver.api.user.data.RoleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
@Qualifier("roleDataLoaderListener")
public class RoleDataLoaderListener implements ApplicationRunner {

    private final RoleManager roleManager;

    public RoleDataLoaderListener(RoleManager roleManager) {
        this.roleManager = roleManager;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Inserting default role definitions");

        Arrays
                .stream(DefaultRole.values())
                .filter(role -> roleManager.getById(role.getId()).isEmpty())
                .collect(Collectors.toList())
                .forEach(role -> roleManager.addRole(role.getId(), role.name()));
    }
}