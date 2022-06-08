package com.ulger.guava.parceldeliveryservice.api.parcel.data;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import org.springframework.stereotype.Component;

@Component
public class DefaultParcelManager implements ParcelManager {

    private final ParcelRepository parcelRepository;
    private final ParcelEntityMapper parcelEntityMapper;

    public DefaultParcelManager(ParcelRepository parcelRepository, ParcelEntityMapper parcelEntityMapper) {
        this.parcelRepository = parcelRepository;
        this.parcelEntityMapper = parcelEntityMapper;
    }

    @Override
    public Parcel save(Parcel parcel) {

        ParcelEntity parcelEntity = parcelEntityMapper.map(parcel);
        parcelRepository.save(parcelEntity);

        ParcelEntity savedEntity = parcelRepository.save(parcelEntity);

        return parcelEntityMapper.map(savedEntity);
    }
}