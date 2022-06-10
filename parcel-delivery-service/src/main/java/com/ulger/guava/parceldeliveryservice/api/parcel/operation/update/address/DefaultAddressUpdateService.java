package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import com.ulger.exception.ApiException;
import com.ulger.guava.parceldeliveryservice.api.ApiReasonCode;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission.AddressUpdatePermissionCheckParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission.AddressUpdatePermissionChecker;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation.AddressUpdateValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class DefaultAddressUpdateService implements AddressUpdateService {

    private final ParcelManager parcelManager;
    private final AddressUpdateValidator updateValidator;
    private final AddressUpdatePermissionChecker permissionChecker;

    public DefaultAddressUpdateService(
            ParcelManager parcelManager,
            AddressUpdateValidator updateValidator,
            AddressUpdatePermissionChecker permissionChecker) {

        this.parcelManager = parcelManager;
        this.updateValidator = updateValidator;
        this.permissionChecker = permissionChecker;
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
                addressUpdateDto.getUserId(),
                addressUpdateDto.getDeliveryAddress());

        Parcel existingParcel = parcelManager
                .findById(addressUpdateDto.getParcelId())
                .orElseThrow(() -> new ApiException(ApiReasonCode.PARCEL_NOT_FOUND.getCode()));

        AddressUpdatePermissionCheckParams permissionCheckParams = new AddressUpdatePermissionCheckParams(
                addressUpdateDto.getUserId(),
                existingParcel.getUserId());

        if (!permissionChecker.check(permissionCheckParams).isPermitted()) {
            log.warn("Illegal parcel update operation detected. Parcel with id '{}' is attempted to update by userId={}. Parcel owner userId is '{}'",
                    addressUpdateDto.getParcelId(),
                    addressUpdateDto.getUserId(),
                    existingParcel.getUserId());

            throw new OperationPermissionException(permissionCheckParams);
        }

        if (Objects.equals(addressUpdateDto.getDeliveryAddress(), existingParcel.getDeliveryAddress())) {
            log.info("Addresses are same, skipping update. SourceAddress={}, targetAddress={}",
                    existingParcel.getDeliveryAddress(),
                    addressUpdateDto.getDeliveryAddress());

            throw new ApiException(ApiReasonCode.SAME_ADDRESS.getCode());
        }
    }
}