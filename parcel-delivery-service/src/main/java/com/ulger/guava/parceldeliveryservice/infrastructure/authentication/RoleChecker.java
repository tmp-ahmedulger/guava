package com.ulger.guava.parceldeliveryservice.infrastructure.authentication;

import java.util.Collection;

public interface RoleChecker {

    boolean hasRole(String roleName);

    boolean hasAnyRole(Collection<String> roleNames);
}