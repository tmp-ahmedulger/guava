package com.ulger.cloud.authenticationserver.rest.api.model;

import com.ulger.usermanager.api.User;

public class UserResponseDto {

    private User user;

    private UserResponseDto() {
    }

    public static UserResponseDto from(User updatedUser) {
        UserResponseDto result = new UserResponseDto();
        result.user = updatedUser;
        return result;
    }

    public Object getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getDisplayName() {
        return user.getDisplayName();
    }
}