package com.ulger.guava.userstoryservice.api.parcel.data;

import com.ulger.guava.userstoryservice.api.parcel.LoadingStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LoadingStatusConverter implements AttributeConverter<LoadingStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LoadingStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public LoadingStatus convertToEntityAttribute(Integer dbData) {
        return LoadingStatus
                .findByCode(dbData)
                .orElseThrow(() -> new NullPointerException("LoadingStatus not found with id : " + dbData));
    }
}