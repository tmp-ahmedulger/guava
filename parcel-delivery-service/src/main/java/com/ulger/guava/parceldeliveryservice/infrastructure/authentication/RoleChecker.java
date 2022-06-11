package com.ulger.guava.parceldeliveryservice.infrastructure.authentication;

public interface RoleChecker {

    boolean hasRole(String roleName);
}