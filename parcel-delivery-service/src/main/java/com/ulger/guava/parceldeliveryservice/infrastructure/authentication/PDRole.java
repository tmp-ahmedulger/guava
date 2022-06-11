package com.ulger.guava.parceldeliveryservice.infrastructure.authentication;

public class PDRole implements Role {

    private String name;

    public PDRole(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}