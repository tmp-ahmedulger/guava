package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.Status;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;
import com.ulger.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class DefaultStatusUpdateValidator implements StatusUpdateValidator {

    @Override
    public ValidationResult validate(StatusUpdateDto statusUpdateDto) {

        ValidationResult validationResult = new ValidationResult();

        if (statusUpdateDto == null) {
            validationResult.addError("Parcel data is required to update status");
            return validationResult;
        }

        if (statusUpdateDto.getUpdaterUserId() == null) {
            validationResult.addError("User id is required");
        }

        if (statusUpdateDto.getStatusCode() == null) {
            validationResult.addError("Status is required");
        } else if (Status.findByCode(statusUpdateDto.getStatusCode()).isEmpty()) {
            validationResult.addError("Unknown status");
        }

        return validationResult;
    }
}