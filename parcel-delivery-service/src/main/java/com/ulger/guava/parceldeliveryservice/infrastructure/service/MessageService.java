package com.ulger.guava.parceldeliveryservice.infrastructure.service;

public interface MessageService {

    String getMessage(String key);

    String getMessage(String key, Object[] args);
}