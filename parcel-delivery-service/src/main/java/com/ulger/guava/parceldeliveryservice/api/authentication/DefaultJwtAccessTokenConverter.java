package com.ulger.guava.parceldeliveryservice.api.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.Map;

public class DefaultJwtAccessTokenConverter extends DefaultAccessTokenConverter {

    private static final String KEY_USER_ID = "userId";

    private final UserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(claims);
        oAuth2Authentication.setDetails(claims);

        Authentication authentication = userTokenConverter.extractAuthentication(claims);

        return new OAuth2Authentication(
                oAuth2Authentication.getOAuth2Request(),
                new DefaultUserAuthentication(extractUserId(claims), authentication));
    }

    private Long extractUserId(Map<String,?> claims) {
        return Long.valueOf((Integer) claims.get(KEY_USER_ID));
    }
}