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

    private UserDao userDao;
    private UserValidator userValidator;
    private CredentialEncoder credentialEncoder;

    public DefaultUserManager(UserDao userDao, UserValidator userValidator, CredentialEncoder credentialEncoder) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.credentialEncoder = credentialEncoder;
    }

    @Override
    public Optional<User> getCustomerByEmail(String email) {
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
                    logger.error("Customer already exist with email {}", customer.getEmail());
                    throw new UserAlreadyExistException("Customer already exist with email '" + customer.getEmail() + "'");
                });

        User user = MutableUser.Builder
                .instance()
                .email(modificationData.getEmail())
                .displayName(modificationData.getDisplayName())
                .credential(credentialEncoder.encode(modificationData.getPassword()))
                .build();

        if (logger.isDebugEnabled()) {
            logger.debug("Customer is inserting");
        }

        User recordedUser = userDao.insert(user);

        if (logger.isDebugEnabled()) {
            logger.debug("Customer saved successfully");
        }

        return recordedUser;
    }
}