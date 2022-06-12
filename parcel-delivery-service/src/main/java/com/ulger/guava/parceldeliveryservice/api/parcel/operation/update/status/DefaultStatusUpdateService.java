package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status;

import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.ResourceNotFoundException;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.StatusUpdateConsentChecker;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.validation.StatusUpdateValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultStatusUpdateService implements StatusUpdateService {

    private final StatusUpdateValidator statusUpdateValidator;
    private final ParcelManager parcelManager;
    private final StatusUpdateConsentChecker updatingPreConditionChecker;

    public DefaultStatusUpdateService(
            StatusUpdateValidator statusUpdateValidator,
            ParcelManager parcelManager,
            StatusUpdateConsentChecker updatingPreConditionChecker) {

        this.statusUpdateValidator = statusUpdateValidator;
        this.parcelManager = parcelManager;
        this.updatingPreConditionChecker = updatingPreConditionChecker;
    }

    @Override
    public boolean update(StatusUpdateDto statusUpdateDto) {

        ValidationResult validationResult = statusUpdateValidator.validate(statusUpdateDto);

        if (validationResult.hasError()) {
            log.error("Parcel status update data is invalid. ValidationResult={}", validationResult.getErrors());
            throw new ValidationException(validationResult.getErrors());
        }

        log.info("Parcel will being updated. parcelId={}, userId={}, statusCode={}",
                statusUpdateDto.getParcelId(),
                statusUpdateDto.getUpdaterUserId(),
                statusUpdateDto.getStatusCode());

        Parcel existingParcel = parcelManager
                .findById(statusUpdateDto.getParcelId())
                .orElseThrow(() -> new ResourceNotFoundException(ApiErrorCode.PARCEL_NOT_FOUND.getKey(), statusUpdateDto.getParcelId()));

        updatingPreConditionChecker.check(existingParcel, statusUpdateDto);

        log.info("Parcel pre-condition check for status updating is completed. Parcel status is being updated");

        boolean isUpdated = parcelManager.updateStatus(
                statusUpdateDto.getParcelId(),
                statusUpdateDto.getStatus());

        if (!isUpdated) {
            log.info("The status of Parcel with id '{}' can not be updated", statusUpdateDto.getParcelId());
        }

        log.info("The status of Parcel with id '{}' has been updated", statusUpdateDto.getParcelId());

        return isUpdated;
    }
}