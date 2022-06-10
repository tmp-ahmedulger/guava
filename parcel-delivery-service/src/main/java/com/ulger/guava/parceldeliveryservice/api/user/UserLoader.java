package com.ulger.guava.parceldeliveryservice.api.user;

import com.ulger.usermanager.api.User;

public interface UserLoader {

    User loadById(long userId);
}