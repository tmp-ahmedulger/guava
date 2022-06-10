package com.ulger.cloud.authenticationserver.authentication;

import com.ulger.usermanager.api.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        DefaultUserDetails defaultUserDetails = (DefaultUserDetails) authentication
                .getUserAuthentication()
                .getPrincipal();

        User user = defaultUserDetails.getUser();

        Map<String, Object> info = new HashMap<>();
        info.put("userId", user.getId());
        info.put("displayName", user.getDisplayName());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}