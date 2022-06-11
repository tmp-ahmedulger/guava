package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatusUpdateDto {

    private Long parcelId;
    private Long updaterUserId;
    private String status;
}