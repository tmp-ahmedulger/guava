package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;
import com.ulger.validation.ValidationResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultParcelCreationValidator implements ParcelCreationValidator {

    @Override
    public ValidationResult validate(ParcelCreationDto parcelCreationDto) {

        ValidationResult validationResult = new ValidationResult();

        if (parcelCreationDto == null) {
            validationResult.addError("Parcel creation update data is required");
            return validationResult;
        }

        if (parcelCreationDto.getUserId() == null) {
            validationResult.addError("User id is required");
        }

        if (StringUtils.isBlank(parcelCreationDto.getDeliveryAddress())) {
            validationResult.addError("Package delivery address is needed");
        }

        if (parcelCreationDto.getWeightInGrams() == null) {
            validationResult.addError("Package weight is required");
        }

        return validationResult;
    }
}