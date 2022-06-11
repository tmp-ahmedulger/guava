package com.ulger.guava.parceldeliveryservice.infrastructure.authentication;

import java.util.Set;

public interface User {


    Long getId();

    String getEmail();

    String getDisplayName();

    Set<Role> getRoles();
}