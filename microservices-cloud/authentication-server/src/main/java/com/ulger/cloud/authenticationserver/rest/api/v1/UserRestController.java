package com.ulger.cloud.authenticationserver.rest.api.v1;

import com.ulger.cloud.authenticationserver.rest.api.model.UserResponseDto;
import com.ulger.usermanager.api.User;
import com.ulger.usermanager.api.UserManager;
import com.ulger.usermanager.api.UserModificationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/v1/user")
public class UserRestController {

    private final UserManager userManager;

    @Autowired
    public UserRestController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(UserModificationData userModificationData) {
        User createdUser = userManager.add(userModificationData);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponseDto.from(createdUser));
    }
}