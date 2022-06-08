package com.ulger.cloud.authenticationserver.configuration;

import com.ulger.cloud.authenticationserver.authentication.JWTTokenEnhancer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final TokenStore tokenStore;
    private final PasswordEncoder passwordEncoder;
    private final JwtAccessTokenConverter accessTokenConverter;
    private final JWTTokenEnhancer jwtTokenEnhancer;

    public OAuth2AuthorizationServerConfig(
            AuthenticationManager authenticationManager,
            TokenStore tokenStore,
            PasswordEncoder passwordEncoder,
            JwtAccessTokenConverter accessTokenConverter,
            JWTTokenEnhancer jwtTokenEnhancer) {

        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenConverter = accessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, accessTokenConverter));

        endpoints
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("parcel-delivery-service")
                .secret(passwordEncoder.encode("pwd-parcel-delivery-service"))
                .scopes("read", "write")
                .authorizedGrantTypes("client_credentials", "authorization_code", "password", "refresh_token")
                .redirectUris("http://localhost:7070/oauth/authorized")
                .autoApprove(true)
                .accessTokenValiditySeconds(200000)
                .refreshTokenValiditySeconds(200000);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}