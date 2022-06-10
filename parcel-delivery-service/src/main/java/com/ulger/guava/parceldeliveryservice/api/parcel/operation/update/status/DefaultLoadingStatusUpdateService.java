package com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status;

import org.springframework.stereotype.Service;

@Service
public class DefaultLoadingStatusUpdateService implements LoadingStatusUpdateService {

    @Override
    public void update(Long parcelId, Long userId, String targetStatus) {

    }
}