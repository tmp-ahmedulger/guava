package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status;

import org.springframework.stereotype.Service;

@Service
public class DefaultStatusUpdateService implements StatusUpdateService {

    @Override
    public void update(Long parcelId, Long userId, String targetStatus) {

    }
}