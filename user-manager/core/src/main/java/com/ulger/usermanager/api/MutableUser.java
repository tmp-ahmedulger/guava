package com.ulger.usermanager.api;

public class MutableUser implements User {

    private Long id;
    private String email;
    private String displayName;
    private String credential;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public static final class Builder {
        private String email;
        private String displayName;
        private String credential;

        private Builder() {
        }

        public static Builder instance() {
            return new Builder();
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder credential(String credential) {
            this.credential = credential;
            return this;
        }

        public MutableUser build() {
            MutableUser mutableCustomer = new MutableUser();
            mutableCustomer.setEmail(email);
            mutableCustomer.setDisplayName(displayName);
            mutableCustomer.setCredential(credential);
            return mutableCustomer;
        }
    }
}