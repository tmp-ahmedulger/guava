package com.ulger.guava.parceldeliveryservice.infrastructure.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.*;
import java.util.stream.Collectors;

public class PDUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_DISPLAY_NAME = "displayName";
    private static final String CLAIM_EMAIL = "email";
    private static final String CLAIM_AUTHORITIES = "authorities";

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication userAuthentication) {
        return super.convertUserAuthentication(userAuthentication);
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> claims) {
        Authentication authentication = super.extractAuthentication(claims);

        User user = PDUser.builder()
                .id(extractUserId(claims))
                .displayName(extractDisplayName(claims))
                .email(extractEmail(claims))
                .roles(extractAuthorities(claims))
                .build();

        return new DefaultUserAuthentication(user, authentication);
    }

    private Long extractUserId(Map<String,?> claims) {
        return Long.valueOf((Integer) claims.get(CLAIM_USER_ID));
    }

    private String extractDisplayName(Map<String,?> claims) {
        return (String) claims.get(CLAIM_DISPLAY_NAME);
    }

    private String extractEmail(Map<String,?> claims) {
        return (String) claims.get(CLAIM_EMAIL);
    }

    private Set<Role> extractAuthorities(Map<String,?> claims) {
        return (Set<Role>) Optional
                .ofNullable(claims.get(CLAIM_AUTHORITIES))
                .filter(List.class::isInstance)
                .map(List.class::cast)
                .orElse(Collections.EMPTY_LIST)
                .stream()
                .map(authority -> new PDRole((String) authority))
                .collect(Collectors.toSet());
    }
}