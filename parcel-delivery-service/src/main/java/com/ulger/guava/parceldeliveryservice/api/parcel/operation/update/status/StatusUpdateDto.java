package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status;

import com.ulger.guava.parceldeliveryservice.api.parcel.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatusUpdateDto {

    private Long parcelId;
    private Long updaterUserId;
    private Integer statusCode;

    public Status getStatus() {
        return Status.findByCode(statusCode).orElse(null);
    }
}