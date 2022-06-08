package com.ulger.usermanager.api;

import com.ulger.usermanager.api.data.UserDao;
import com.ulger.usermanager.api.validation.UserValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DefaultUserManager implements UserManager {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserManager.class);

    private final UserDao userDao;
    private final UserValidator userValidator;
    private final UserPreInitiator userPreInitiator;

    public DefaultUserManager(UserDao userDao, UserValidator userValidator, UserPreInitiator userPreInitiator) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.userPreInitiator = userPreInitiator;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Email is required");
        }

        return userDao.findByEmail(email);
    }

    @Override
    public User add(UserModificationData modificationData) {
        ValidationResult validationResult = userValidator.validateToCreate(modificationData);

        if (validationResult.hasError()) {
            throw new ValidationException(validationResult.getErrors());
        }

        userDao
                .findByEmail(modificationData.getEmail())
                .ifPresent(customer -> {
                    logger.error("User already exist with email {}", customer.getEmail());
                    throw new UserAlreadyExistException("User already exist with email '" + customer.getEmail() + "'");
                });

        User user = userPreInitiator.initiate(modificationData);

        if (logger.isDebugEnabled()) {
            logger.debug("User is inserting");
        }

        User recordedUser = userDao.insert(user);

        if (logger.isDebugEnabled()) {
            logger.debug("User saved successfully");
        }

        return recordedUser;
    }
}