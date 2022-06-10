package com.ulger.guava.parceldeliveryservice.api.user;

import com.ulger.usermanager.api.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class UserLoaderProvider implements UserLoader {

    private final List<UserLoader> userLoaders;

    public UserLoaderProvider() {
        this.userLoaders = new LinkedList<>();
    }

    public UserLoaderProvider(List<UserLoader> userLoaders) {
        this.userLoaders = userLoaders;
    }

    public void addUserManager(UserLoader userManager) {
        this.userLoaders.add(userManager);
    }

    @Override
    public User loadById(long userId) {
        return null;
    }
}