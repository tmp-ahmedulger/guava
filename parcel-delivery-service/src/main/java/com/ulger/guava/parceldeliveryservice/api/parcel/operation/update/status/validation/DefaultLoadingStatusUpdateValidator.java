package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.validation;

import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.LoadingStatusUpdateDto;
import com.ulger.validation.ValidationResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultLoadingStatusUpdateValidator implements LoadingStatusUpdateValidator {

    @Override
    public ValidationResult validate(LoadingStatusUpdateDto loadingStatusUpdateDto) {

        ValidationResult validationResult = new ValidationResult();

        if (loadingStatusUpdateDto == null) {
            validationResult.addError("Parcel data is required to update loading status");
            return validationResult;
        }

        if (loadingStatusUpdateDto.getUpdaterUserId() == null) {
            validationResult.addError("User id is required");
        }

        if (StringUtils.isBlank(loadingStatusUpdateDto.getLoadingStatus())) {
            validationResult.addError("Loading status is required");
        }

        return validationResult;
    }
}