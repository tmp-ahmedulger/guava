package com.ulger.guava.parceldeliveryservice.api.user;

import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.User;

public interface UserLoader {

    User loadById(long userId);
}