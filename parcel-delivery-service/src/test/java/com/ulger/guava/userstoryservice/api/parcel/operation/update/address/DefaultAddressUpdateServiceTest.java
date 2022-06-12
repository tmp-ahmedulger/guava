package com.ulger.guava.userstoryservice.api.parcel.operation.update.address;

import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.DefaultAddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.AddressUpdateConsentChecker;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation.AddressUpdateValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

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
        MockitoAnnotations.openMocks(this);

        this.addressUpdateService = new DefaultAddressUpdateService(
                parcelManager,
                updateValidator,
                updatingPreConditionChecker
        );
    }

    @Test
    public void test_update_throws_exception_when_given_data_is_invalid() {

        // given
        ValidationResult validationResult = new ValidationResult();
        validationResult.addError("errorX");
        validationResult.addError("errorY");

        Mockito
                .when(updateValidator.validate(any()))
                .thenReturn(validationResult);

        // when
        ValidationException thrownException = Assertions.catchThrowableOfType(
                () -> addressUpdateService.update(null),
                ValidationException.class
        );

        // then
        Assertions.assertThat(thrownException.getErrors()).isNotEmpty();
        Assertions.assertThat(thrownException.getErrors().size()).isEqualTo(2);
        Assertions.assertThat(thrownException.getErrors()).containsAll(Set.of("errorX", "errorY"));
    }
}