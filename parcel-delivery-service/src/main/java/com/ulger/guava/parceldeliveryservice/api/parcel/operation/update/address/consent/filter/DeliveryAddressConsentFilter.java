package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter;

import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@Qualifier("deliveryAddressConsentFilter")
public class DeliveryAddressConsentFilter implements AddressUpdateConsentFilter {

    @Override
    public void check(AddressUpdateConsentFilterParams params) {

        String sourceDeliveryAddress = params.getParcel().getDeliveryAddress();
        String targetAddress = params.getAddressUpdateDto().getDeliveryAddress();

        if (Objects.equals(sourceDeliveryAddress, targetAddress)) {
            log.info("Addresses are same, skipping update. SourceAddress={}, targetAddress={}",
                    sourceDeliveryAddress,
                    targetAddress);

            throw new ConsentFilterException(new ApiException(ApiErrorCode.SAME_ADDRESS.getKey()));
        }
    }
}