package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import com.ulger.exception.ApiException;
import com.ulger.guava.parceldeliveryservice.api.ApiReasonCode;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission.AddressUpdatePermissionCheckParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission.AddressUpdatePermissionChecker;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation.ParcelAddressUpdateValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class DefaultParcelAddressUpdateService implements ParcelAddressUpdateService {

    private final ParcelManager parcelManager;
    private final ParcelAddressUpdateValidator updateValidator;
    private final AddressUpdatePermissionChecker permissionChecker;

    public DefaultParcelAddressUpdateService(
            ParcelManager parcelManager,
            ParcelAddressUpdateValidator updateValidator,
            AddressUpdatePermissionChecker permissionChecker) {

        this.parcelManager = parcelManager;
        this.updateValidator = updateValidator;
        this.permissionChecker = permissionChecker;
    }

    @Override
    public void update(ParcelAddressUpdateDto parcelAddressUpdateDto) {

        ValidationResult validationResult = updateValidator.validate(parcelAddressUpdateDto);

        if (validationResult.hasError()) {
            log.error("Parcel address update data is invalid. ValidationResult={}", validationResult.getErrors());
            throw new ValidationException(validationResult.getErrors());
        }

        log.info("Parcel will being updated. parcelId={}, userId={}, deliveryAddress={}",
                parcelAddressUpdateDto.getParcelId(),
                parcelAddressUpdateDto.getUserId(),
                parcelAddressUpdateDto.getDeliveryAddress());

        Parcel existingParcel = parcelManager
                .findById(parcelAddressUpdateDto.getParcelId())
                .orElseThrow(() -> new ApiException(ApiReasonCode.PARCEL_NOT_FOUND.getCode()));


        AddressUpdatePermissionCheckParams permissionCheckParams = new AddressUpdatePermissionCheckParams(
                parcelAddressUpdateDto.getUserId(),
                existingParcel.getUserId());

        if (!permissionChecker.check(permissionCheckParams).isPermitted()) {
            log.warn("Illegal parcel update operation detected. Parcel with id '{}' is attempted to update by userId={}. Parcel owner userId is '{}'",
                    parcelAddressUpdateDto.getParcelId(),
                    parcelAddressUpdateDto.getUserId(),
                    existingParcel.getUserId());

            throw new OperationPermissionException(permissionCheckParams);
        }

        if (Objects.equals(parcelAddressUpdateDto.getDeliveryAddress(), existingParcel.getDeliveryAddress())) {
            log.info("Addresses are same, skipping update. SourceAddress={}, targetAddress={}",
                    existingParcel.getDeliveryAddress(),
                    parcelAddressUpdateDto.getDeliveryAddress());

            throw new ApiException(ApiReasonCode.SAME_ADDRESS.getCode());
        }
    }
}