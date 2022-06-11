package com.ulger.guava.parceldeliveryservice.infrastructure.authentication;

import org.springframework.security.core.Authentication;

/**
 * Keeps additional user information
 */
public interface PDUserAuthentication extends Authentication {

    Long getUserId();

    String getDisplayName();
}