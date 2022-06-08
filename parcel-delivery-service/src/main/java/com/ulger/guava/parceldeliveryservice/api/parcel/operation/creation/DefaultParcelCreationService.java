package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultParcelCreationService implements ParcelCreationService {

    private final ParcelCreationValidator parcelCreationValidator;
    private final ParcelManager parcelManager;
    private final ParcelCreationDtoMapper parcelCreationDtoMapper;

    public DefaultParcelCreationService(ParcelCreationValidator parcelCreationValidator, ParcelManager parcelManager, ParcelCreationDtoMapper parcelCreationDtoMapper) {
        this.parcelCreationValidator = parcelCreationValidator;
        this.parcelManager = parcelManager;
        this.parcelCreationDtoMapper = parcelCreationDtoMapper;
    }

    @Override
    public Parcel create(ParcelCreationDto parcelCreationDto) {

        if (log.isDebugEnabled()) {
            log.debug("Parcel creating with data={}", parcelCreationDto);
        }

        ValidationResult validationResult = parcelCreationValidator.validate(parcelCreationDto);

        if (validationResult.hasError()) {
            log.error("Parcel creation data is invalid. ValidationResult={}", validationResult.getErrors());
            throw new ValidationException(validationResult.getErrors());
        }

        Parcel parcel = parcelCreationDtoMapper.map(parcelCreationDto);

        Parcel savedParcel = parcelManager.save(parcel);
        log.info("Parcel created");

        return savedParcel;
    }
}