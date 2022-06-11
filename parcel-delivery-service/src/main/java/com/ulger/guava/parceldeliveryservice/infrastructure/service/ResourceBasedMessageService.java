package com.ulger.guava.parceldeliveryservice.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ResourceBasedMessageService implements MessageService {

    private final Object[] emptyArguments = new Object[] {};

    private final MessageSource messageSource;

    @Override
    public String getMessage(String key) {
        return messageSource.getMessage(key, emptyArguments, Locale.ENGLISH);
    }

    @Override
    public String getMessage(String key, Object[] args) {
        return messageSource.getMessage(key, args, Locale.ENGLISH);
    }
}