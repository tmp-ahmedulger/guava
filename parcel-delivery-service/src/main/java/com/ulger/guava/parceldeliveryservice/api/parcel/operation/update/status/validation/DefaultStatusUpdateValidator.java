package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;
import com.ulger.validation.ValidationResult;
import org.apache.commons.lang.StringUtils;
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

        if (StringUtils.isBlank(statusUpdateDto.getStatus())) {
            validationResult.addError("Status is required");
        }

        return validationResult;
    }
}