package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.ParcelAddressUpdateDto;
import com.ulger.validation.ValidationResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultAddressParcelUpdateValidator implements ParcelAddressUpdateValidator {

    @Override
    public ValidationResult validate(ParcelAddressUpdateDto parcelAddressUpdateDto) {

        ValidationResult validationResult = new ValidationResult();

        if (parcelAddressUpdateDto == null) {
            validationResult.addError("Parcel address update data is required");
            return validationResult;
        }

        if (parcelAddressUpdateDto.getUserId() == null) {
            validationResult.addError("User id is required");
        }

        if (StringUtils.isBlank(parcelAddressUpdateDto.getDeliveryAddress())) {
            validationResult.addError("Package delivery address is needed");
        }

        return validationResult;
    }
}