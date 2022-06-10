package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address;

import com.ulger.exception.ApiException;
import com.ulger.guava.parceldeliveryservice.api.ApiReasonCode;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelEntity;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelRepository;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class DefaultParcelAddressUpdateService implements ParcelAddressUpdateService {

    private final ParcelRepository parcelRepository;
    private final ParcelAddressUpdateValidator parcelAddressUpdateValidator;

    public DefaultParcelAddressUpdateService(ParcelRepository parcelRepository, ParcelAddressUpdateValidator parcelAddressUpdateValidator) {
        this.parcelRepository = parcelRepository;
        this.parcelAddressUpdateValidator = parcelAddressUpdateValidator;
    }

    @Override
    public void update(ParcelAddressUpdateDto parcelAddressUpdateDto) {

        ValidationResult validationResult = parcelAddressUpdateValidator.validate(parcelAddressUpdateDto);

        if (validationResult.hasError()) {
            log.error("Parcel address update data is invalid. ValidationResult={}", validationResult.getErrors());
            throw new ValidationException(validationResult.getErrors());
        }

        log.info("Parcel will being updated. parcelId={}, userId={}, deliveryAddress={}",
                parcelAddressUpdateDto.getParcelId(),
                parcelAddressUpdateDto.getUserId(),
                parcelAddressUpdateDto.getDeliveryAddress());

        ParcelEntity parcelEntity = parcelRepository
                .findById(parcelAddressUpdateDto.getParcelId())
                .orElseThrow(() -> new ApiException(ApiReasonCode.PARCEL_NOT_FOUND.getCode()));

        if (!Objects.equals(parcelAddressUpdateDto.getUserId(), parcelEntity.getUserId())) {
            log.warn("Illegal parcel update operation detected. Parcel with id '{}' is attempted to update by userId={}. Parcel owner userId is '{}'",
                    parcelAddressUpdateDto.getParcelId(),
                    parcelAddressUpdateDto.getUserId(),
                    parcelEntity.getUserId());

            throw new OperationPermissionException(parcelAddressUpdateDto.getUserId());
        }

        if (Objects.equals(parcelAddressUpdateDto.getDeliveryAddress(), parcelEntity.getDeliveryAddress())) {
            log.info("Addresses are same, skipping update. SourceAddress={}, targetAddress={}",
                    parcelEntity.getDeliveryAddress(),
                    parcelAddressUpdateDto.getDeliveryAddress());

            throw new ApiException(ApiReasonCode.SAME_ADDRESS.getCode());
        }
    }
}