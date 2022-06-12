package com.ulger.guava.parceldeliveryservice.api.parcel.data.converter;

import com.ulger.guava.parceldeliveryservice.api.parcel.State;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StateConverter implements AttributeConverter<State, Integer> {

    @Override
    public Integer convertToDatabaseColumn(State attribute) {
        return attribute.getCode();
    }

    @Override
    public State convertToEntityAttribute(Integer dbData) {
        return State
                .findByCode(dbData)
                .orElseThrow(() -> new NullPointerException("State not found with id : " + dbData));
    }
}