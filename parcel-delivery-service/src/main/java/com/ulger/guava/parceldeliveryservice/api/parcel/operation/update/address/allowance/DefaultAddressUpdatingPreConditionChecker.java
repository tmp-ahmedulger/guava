package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.allowance;

import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.OperationPermissionException;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.PreConditionException;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission.AddressUpdatePermissionCheckParams;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.permission.AddressUpdatePermissionChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class DefaultAddressUpdatingPreConditionChecker implements AddressUpdatingPreConditionChecker {

    private final AddressUpdatePermissionChecker permissionChecker;

    public DefaultAddressUpdatingPreConditionChecker(AddressUpdatePermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }

    @Override
    public void check(Parcel existingParcel, AddressUpdateDto addressUpdateDto) {
        AddressUpdatePermissionCheckParams permissionCheckParams = new AddressUpdatePermissionCheckParams(
                addressUpdateDto.getUpdaterUserId(),
                existingParcel.getOwnerUserId());

        if (!permissionChecker.check(permissionCheckParams).isPermitted()) {
            log.warn("Illegal parcel update operation detected. Parcel with id '{}' is attempted to update by userId={}. Parcel owner userId is '{}'",
                    addressUpdateDto.getParcelId(),
                    addressUpdateDto.getUpdaterUserId(),
                    existingParcel.getOwnerUserId());

            throw new PreConditionException(new OperationPermissionException(permissionCheckParams));
        }

        if (Objects.equals(addressUpdateDto.getDeliveryAddress(), existingParcel.getDeliveryAddress())) {
            log.info("Addresses are same, skipping update. SourceAddress={}, targetAddress={}",
                    existingParcel.getDeliveryAddress(),
                    addressUpdateDto.getDeliveryAddress());

            throw new PreConditionException(ApiErrorCode.SAME_ADDRESS.getKey());
        }
    }
}