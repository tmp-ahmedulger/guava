package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent;

import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilter;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter.StatusUpdateConsentFilterParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultStatusUpdateConsentChecker implements StatusUpdateConsentChecker {

    private final ConsentFilter<StatusUpdateConsentFilterParams> consentFilter;

    public DefaultStatusUpdateConsentChecker(
            @Qualifier("statusUpdateConsentFilterProvider") ConsentFilter<StatusUpdateConsentFilterParams> consentFilter) {
        this.consentFilter = consentFilter;
    }

    @Override
    public void check(Parcel existingParcel, StatusUpdateDto statusUpdateDto) {
        if (existingParcel == null) {
            throw new IllegalArgumentException("Parcel data is required for status update checking");
        }

        if (statusUpdateDto == null) {
            throw new IllegalArgumentException("New status update data is required for status update checking");
        }

        StatusUpdateConsentFilterParams consentCheckParams = new StatusUpdateConsentFilterParams(existingParcel, statusUpdateDto);

        consentFilter.check(consentCheckParams);
    }
}