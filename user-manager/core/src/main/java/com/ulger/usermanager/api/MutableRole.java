package com.ulger.usermanager.api;

public class MutableRole implements Role {

    private Long id;
    private String name;

    public MutableRole(Long id) {
        this.id = id;
    }

    public MutableRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}