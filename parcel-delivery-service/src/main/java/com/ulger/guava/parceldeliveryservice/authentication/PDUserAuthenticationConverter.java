package com.ulger.guava.parceldeliveryservice.authentication;

import com.ulger.usermanager.api.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Map;

public class PDUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_DISPLAY_NAME = "displayName";

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
                .build();

        return new DefaultUserAuthentication(user, authentication);
    }

    private Long extractUserId(Map<String,?> claims) {
        return Long.valueOf((Integer) claims.get(CLAIM_USER_ID));
    }

    private String extractDisplayName(Map<String,?> claims) {
        return (String) claims.get(CLAIM_DISPLAY_NAME);
    }
}