package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.validation.ValidationResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultParcelCreationValidator implements ParcelCreationValidator {

    @Override
    public ValidationResult validate(ParcelCreationDto parcelCreationDto) {

        ValidationResult validationResult = new ValidationResult();

        if (parcelCreationDto == null) {
            throw new IllegalArgumentException("");
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