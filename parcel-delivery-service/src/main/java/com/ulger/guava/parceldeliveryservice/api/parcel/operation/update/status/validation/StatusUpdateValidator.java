package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;
import com.ulger.validation.ValidationResult;

public interface StatusUpdateValidator {

    ValidationResult validate(StatusUpdateDto statusUpdateDto);
}