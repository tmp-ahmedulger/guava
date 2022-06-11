package com.ulger.guava.parceldeliveryservice.api.user;

import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("remoteUserLoader")
public class RemoteUserLoader implements UserLoader {

    @Override
    public User loadById(long userId) {
        return null;
    }
}