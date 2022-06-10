package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.ParcelAddressUpdateDto;
import com.ulger.validation.ValidationResult;

public interface ParcelAddressUpdateValidator {

    ValidationResult validate(ParcelAddressUpdateDto parcelAddressUpdateDto);
}