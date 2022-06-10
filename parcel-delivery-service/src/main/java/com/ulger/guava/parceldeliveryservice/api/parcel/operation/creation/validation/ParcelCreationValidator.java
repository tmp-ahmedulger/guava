package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;
import com.ulger.validation.ValidationResult;

public interface ParcelCreationValidator {

    ValidationResult validate(ParcelCreationDto parcelCreationDto);
}