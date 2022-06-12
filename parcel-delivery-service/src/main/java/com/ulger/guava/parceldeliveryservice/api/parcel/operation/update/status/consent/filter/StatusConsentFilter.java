package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.consent.filter;

import com.ulger.guava.parceldeliveryservice.api.ApiErrorCode;
import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException;
import com.ulger.guava.parceldeliveryservice.api.parcel.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("statusConsentFilter")
public class StatusConsentFilter implements StatusUpdateConsentFilter {

    @Override
    public void check(StatusUpdateConsentFilterParams params) {

        Status sourceStatus = params.getParcel().getStatus();
        Status targetStatus = params.getStatusUpdateDto().getStatus();

        if (sourceStatus == targetStatus) {
            log.info("Statuses are same, skipping update. sourceLStatus={}, targetStatus={}", sourceStatus, targetStatus);
            throw new ConsentFilterException(new ApiException(ApiErrorCode.SAME_STATUS.getKey()));
        }
    }
}