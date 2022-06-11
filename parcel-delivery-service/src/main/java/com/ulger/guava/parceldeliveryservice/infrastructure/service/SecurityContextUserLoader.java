package com.ulger.guava.parceldeliveryservice.infrastructure.service;

import com.ulger.guava.parceldeliveryservice.api.user.UserLoader;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.DefaultUserAuthentication;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.SecurityContextHelper;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("securityContextUserLoader")
public class SecurityContextUserLoader implements UserLoader {

    @Override
    public User loadById(long userId) {

        return SecurityContextHelper
                .getAuthentication()
                .map(DefaultUserAuthentication::getUser)
                .orElse(null);
    }
}