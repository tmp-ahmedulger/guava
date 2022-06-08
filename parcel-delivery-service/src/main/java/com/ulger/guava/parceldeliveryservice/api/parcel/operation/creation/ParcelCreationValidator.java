package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.validation.ValidationResult;

public interface ParcelCreationValidator {

    ValidationResult validate(ParcelCreationDto parcelCreationDto);
}