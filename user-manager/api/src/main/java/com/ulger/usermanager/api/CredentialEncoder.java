package com.ulger.usermanager.api;

/**
 *  This interface is a contract for password encoding operation.
 *  Different algorithms can be applied for hashing.
 */
public interface CredentialEncoder {

    /**
     *
     * @param raw password is a string represents a password none hashed
     * @return hashed password
     */
    String encode(String raw);
}