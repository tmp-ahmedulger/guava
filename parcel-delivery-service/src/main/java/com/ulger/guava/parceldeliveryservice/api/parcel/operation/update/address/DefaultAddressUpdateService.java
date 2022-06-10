package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import com.ulger.exception.ApiException;
import com.ulger.guava.parceldeliveryservice.api.ApiReasonCode;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.allowance.AddressUpdatingPreConditionChecker;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation.AddressUpdateValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultAddressUpdateService implements AddressUpdateService {

    private final ParcelManager parcelManager;
    private final AddressUpdateValidator updateValidator;
    private final AddressUpdatingPreConditionChecker updatingPreConditionChecker;

    public DefaultAddressUpdateService(
            ParcelManager parcelManager,
            AddressUpdateValidator updateValidator,
            AddressUpdatingPreConditionChecker updatingPreConditionChecker) {

        this.parcelManager = parcelManager;
        this.updateValidator = updateValidator;
        this.updatingPreConditionChecker = updatingPreConditionChecker;
    }

    @Override
    public void update(AddressUpdateDto addressUpdateDto) {

        ValidationResult validationResult = updateValidator.validate(addressUpdateDto);

        if (validationResult.hasError()) {
            log.error("Parcel address update data is invalid. ValidationResult={}", validationResult.getErrors());
            throw new ValidationException(validationResult.getErrors());
        }

        log.info("Parcel will being updated. parcelId={}, userId={}, deliveryAddress={}",
                addressUpdateDto.getParcelId(),
                addressUpdateDto.getUpdaterUserId(),
                addressUpdateDto.getDeliveryAddress());

        Parcel existingParcel = parcelManager
                .findById(addressUpdateDto.getParcelId())
                .orElseThrow(() -> new ApiException(ApiReasonCode.PARCEL_NOT_FOUND.getCode()));

        updatingPreConditionChecker.check(existingParcel, addressUpdateDto);

        log.info("Parcel pre-condition check for address updating is completed. Parcel address is being updated");

        parcelManager.updateDeliveryAddress(addressUpdateDto.getParcelId(), addressUpdateDto.getDeliveryAddress());

        log.info("The address of Parcel with id '{}' has been updated", addressUpdateDto.getParcelId());
    }
}