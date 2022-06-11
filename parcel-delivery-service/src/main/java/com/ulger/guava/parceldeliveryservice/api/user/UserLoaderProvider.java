package com.ulger.guava.parceldeliveryservice.api.user;

import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
@Qualifier("userLoaderProvider")
public class UserLoaderProvider implements UserLoader {

    private final List<UserLoader> userLoaders;

    public UserLoaderProvider() {
        this.userLoaders = new LinkedList<>();
    }

    public UserLoaderProvider(List<UserLoader> userLoaders) {
        this.userLoaders = userLoaders;
    }

    @Override
    public User loadById(long userId) {

        for (UserLoader userLoader : userLoaders) {
            log.info("User is loading by {}", userLoader);

            User user = userLoader.loadById(userId);

            if (user != null) {
                log.info("User found by {}", userLoader);
                return user;
            }
        }

        return null;
    }
}