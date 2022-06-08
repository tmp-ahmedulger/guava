package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class DefaultParcelCreationValidator implements ParcelCreationValidator {

    @Override
    public ValidationResult validate(ParcelCreationDto parcelCreationDto) {
        return null;
    }
}