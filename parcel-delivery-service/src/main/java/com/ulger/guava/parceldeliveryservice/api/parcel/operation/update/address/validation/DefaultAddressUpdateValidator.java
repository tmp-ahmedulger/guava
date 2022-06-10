package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import com.ulger.validation.ValidationResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultAddressUpdateValidator implements AddressUpdateValidator {

    @Override
    public ValidationResult validate(AddressUpdateDto addressUpdateDto) {

        ValidationResult validationResult = new ValidationResult();

        if (addressUpdateDto == null) {
            validationResult.addError("Parcel address update data is required");
            return validationResult;
        }

        if (addressUpdateDto.getUserId() == null) {
            validationResult.addError("User id is required");
        }

        if (StringUtils.isBlank(addressUpdateDto.getDeliveryAddress())) {
            validationResult.addError("Package delivery address is needed");
        }

        return validationResult;
    }
}