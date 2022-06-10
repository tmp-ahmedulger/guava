package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.LoadingStatusUpdateDto;
import com.ulger.validation.ValidationResult;

public interface LoadingStatusUpdateValidator {

    ValidationResult validate(LoadingStatusUpdateDto loadingStatusUpdateDto);
}