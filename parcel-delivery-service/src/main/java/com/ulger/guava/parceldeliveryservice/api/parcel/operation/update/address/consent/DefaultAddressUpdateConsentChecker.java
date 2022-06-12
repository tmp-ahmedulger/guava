package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent;

import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.filter.AddressUpdateConsentFilterParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultAddressUpdateConsentChecker implements AddressUpdateConsentChecker {

    private final ConsentFilter<AddressUpdateConsentFilterParams> consentFilter;

    public DefaultAddressUpdateConsentChecker(
            @Qualifier("addressUpdateConsentFilterProvider") ConsentFilter<AddressUpdateConsentFilterParams> consentFilter) {
        this.consentFilter = consentFilter;
    }

    @Override
    public void check(Parcel existingParcel, AddressUpdateDto addressUpdateDto) {
        if (existingParcel == null) {
            throw new IllegalArgumentException("Parcel data is required for address update checking");
        }

        if (addressUpdateDto == null) {
            throw new IllegalArgumentException("New Address update data is required for address update checking");
        }

        AddressUpdateConsentFilterParams consentCheckParams = new AddressUpdateConsentFilterParams(existingParcel, addressUpdateDto);

        consentFilter.check(consentCheckParams);
    }
}