package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.Status;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DefaultParcelManager implements ParcelManager {

    private final ParcelRepository parcelRepository;
    private final ParcelEntityMapper parcelEntityMapper;

    public DefaultParcelManager(ParcelRepository parcelRepository, ParcelEntityMapper parcelEntityMapper) {
        this.parcelRepository = parcelRepository;
        this.parcelEntityMapper = parcelEntityMapper;
    }

    @Override
    public Optional<Parcel> findById(Long id) {
        return parcelRepository
                .findById(id)
                .map(parcelEntityMapper::mapFromEntity);
    }

    @Override
    public Parcel save(Parcel parcel) {

        ParcelEntity parcelEntity = parcelEntityMapper.mapToEntity(parcel);
        parcelRepository.save(parcelEntity);

        ParcelEntity savedEntity = parcelRepository.save(parcelEntity);

        return parcelEntityMapper.mapFromEntity(savedEntity);
    }

    @Override
    @Transactional
    public boolean updateDeliveryAddress(long parcelId, String deliveryAddress) {
        return parcelRepository.updateDeliveryAddress(parcelId, deliveryAddress) > 0;
    }

    @Override
    @Transactional
    public boolean updateStatus(long parcelId, Status status) {
        return parcelRepository.updateStatus(parcelId, status) > 0;
    }
}