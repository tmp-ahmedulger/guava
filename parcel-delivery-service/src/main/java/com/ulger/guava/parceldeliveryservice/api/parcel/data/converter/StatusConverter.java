package com.ulger.guava.parceldeliveryservice.api.parcel.data.converter;

import com.ulger.guava.parceldeliveryservice.api.parcel.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status attribute) {
        return attribute.getCode();
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        return Status
                .findByCode(dbData)
                .orElseThrow(() -> new NullPointerException("Status not found with id : " + dbData));
    }
}