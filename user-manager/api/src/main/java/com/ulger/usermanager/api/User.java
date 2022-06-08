package com.ulger.usermanager.api;

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
}