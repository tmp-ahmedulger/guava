package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import com.ulger.validation.ValidationResult;

public interface AddressUpdateValidator {

    ValidationResult validate(AddressUpdateDto addressUpdateDto);
}