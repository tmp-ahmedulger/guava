package com.ulger.usermanager.api;

import java.util.Set;

public interface User {

    /**
     * Represents unique identifier of customer
     * @return id
     */
    Long getId();

    /**
     * Represent email of user
     * @return email
     */
    String getEmail();

    /**
     * Represents concatenation of name and surname
     * @return full name
     */
    String getDisplayName();

    /**
     * Represents raw or hashed user password
     * @return credential
     */
    String getCredential();

    /**
     * Keeps which roles user has
     * @return role list
     */
    Set<Role> getRoles();
}