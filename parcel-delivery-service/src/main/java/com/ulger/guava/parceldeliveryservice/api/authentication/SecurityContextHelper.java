package com.ulger.guava.parceldeliveryservice.api.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityContextHelper {

    public static Optional<DefaultUserAuthentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(DefaultUserAuthentication.class::isInstance)
                .map(DefaultUserAuthentication.class::cast);
    }
}