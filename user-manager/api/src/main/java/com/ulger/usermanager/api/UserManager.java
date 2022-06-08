package com.ulger.usermanager.api;

import java.util.Optional;

public interface UserManager {

    /**
     *
     * @param email
     * @throws IllegalArgumentException if given email is blank
     * @return Customer if matches with email or Optional.empty if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Adds new customer
     *
     * @throws com.ulger.validation.ValidationException if given input is not valid
     * @param userModificationData
     * @return Saved customer instance
     */
    User add(UserModificationData userModificationData);
}