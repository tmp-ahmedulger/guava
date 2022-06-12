package com.ulger.guava.userstoryservice.api.parcel.operation.update.address;

import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.DefaultAddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.AddressUpdateConsentChecker;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation.AddressUpdateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

public class DefaultAddressUpdateServiceTest  {

    @Mock
    private ParcelManager parcelManager;

    @Mock
    private AddressUpdateValidator updateValidator;

    @Mock
    private AddressUpdateConsentChecker updatingPreConditionChecker;

    private AddressUpdateService addressUpdateService;

    @BeforeEach
    void setUp() {
        this.addressUpdateService = new DefaultAddressUpdateService(
                parcelManager,
                updateValidator,
                updatingPreConditionChecker
        );
    }
}