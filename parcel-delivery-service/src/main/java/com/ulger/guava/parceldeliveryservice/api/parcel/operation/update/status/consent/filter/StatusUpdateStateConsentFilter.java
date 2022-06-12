package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter;

import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("statusUpdateStateConsentFilter")
public class StatusUpdateStateConsentFilter implements StatusUpdateConsentFilter {

    @Override
    public void check(StatusUpdateConsentFilterParams params) {
        if (params.getParcel().getState().isCancelled()) {
            throw new ConsentFilterException(new ApiException(ApiErrorCode.CANCELLED_UPDATE_ATTEMPT.getKey()));
        }
    }
}