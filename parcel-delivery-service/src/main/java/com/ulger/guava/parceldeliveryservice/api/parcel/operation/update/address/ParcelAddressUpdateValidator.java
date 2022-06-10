package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import com.ulger.validation.ValidationResult;

public interface ParcelAddressUpdateValidator {

    ValidationResult validate(ParcelAddressUpdateDto parcelAddressUpdateDto);
}