package com.ulger.usermanager.api.data;

import com.ulger.usermanager.api.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByEmail(String email);

    User insert(User user);
}