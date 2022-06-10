package com.ulger.guava.parceldeliveryservice.api.parcel;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultBarcodeGenerator implements BarcodeGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}