package com.ulger.guava.parceldeliveryservice.api.authentication;

import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Optional;

public class SecurityContextHelper {

    public static Optional<DefaultUserAuthentication> getAuthentication() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(OAuth2Authentication.class::isInstance)
                .map(OAuth2Authentication.class::cast)
                .map(OAuth2Authentication::getUserAuthentication)
                .filter(DefaultUserAuthentication.class::isInstance)
                .map(DefaultUserAuthentication.class::cast);
    }

    public static DefaultUserAuthentication getAuthenticated() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(OAuth2Authentication.class::isInstance)
                .map(OAuth2Authentication.class::cast)
                .map(OAuth2Authentication::getUserAuthentication)
                .filter(DefaultUserAuthentication.class::isInstance)
                .map(DefaultUserAuthentication.class::cast)
                .orElseThrow(() -> new AuthorizationServiceException("Authentication object not found"));
    }
}