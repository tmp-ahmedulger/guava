package com.ulger.guava.parceldeliveryservice.api.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

import java.util.Map;

public class DefaultJwtAccessTokenConverter extends DefaultAccessTokenConverter {

    private final UserAuthenticationConverter userTokenConverter = new PDUserAuthenticationConverter();

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> claims) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(claims);
        oAuth2Authentication.setDetails(claims);

        Authentication authentication = userTokenConverter.extractAuthentication(claims);

        return new OAuth2Authentication(oAuth2Authentication.getOAuth2Request(), authentication);
    }
}