package com.ulger.guava.parceldeliveryservice.infrastructure.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.PostConstruct;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final JwtAccessTokenConverter accessTokenConverter;
    private final RemoteTokenServices remoteTokenServices;

    public ResourceServerConfiguration(JwtAccessTokenConverter accessTokenConverter, RemoteTokenServices remoteTokenServices) {
        this.accessTokenConverter = accessTokenConverter;
        this.remoteTokenServices = remoteTokenServices;
    }

    @PostConstruct
    public void init() {
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .anyRequest()
                    .authenticated();
    }
}