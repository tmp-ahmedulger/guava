package com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation;

import com.ulger.guava.parceldeliveryservice.api.parcel.BarcodeGenerator;
import com.ulger.guava.parceldeliveryservice.api.parcel.LoadingStatus;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.ParcelMutator;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.validation.ParcelCreationValidator;
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
    private final BarcodeGenerator barcodeGenerator;

    public DefaultParcelCreationService(
            ParcelCreationValidator parcelCreationValidator,
            ParcelManager parcelManager,
            ParcelCreationDtoMapper parcelCreationDtoMapper,
            BarcodeGenerator barcodeGenerator) {

        this.parcelCreationValidator = parcelCreationValidator;
        this.parcelManager = parcelManager;
        this.parcelCreationDtoMapper = parcelCreationDtoMapper;
        this.barcodeGenerator = barcodeGenerator;
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

        Parcel mappedParcel = parcelCreationDtoMapper.map(parcelCreationDto);

        ParcelMutator parcelMutator = ParcelMutator.of(mappedParcel);
        parcelMutator.setLoadingStatus(LoadingStatus.CREATED);
        parcelMutator.setBarcode(barcodeGenerator.generate());

        log.info("Parcel is being created with barcode={}", parcelMutator.getBarcode());

        Parcel savedParcel = parcelManager.save(parcelMutator);
        log.info("Parcel created");

        return savedParcel;
    }
}