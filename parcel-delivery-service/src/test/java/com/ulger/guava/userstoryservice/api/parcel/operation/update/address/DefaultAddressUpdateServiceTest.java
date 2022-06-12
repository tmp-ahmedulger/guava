package com.ulger.guava.userstoryservice.api.parcel.operation.update.address;

import com.ulger.guava.parceldeliveryservice.api.ResourceNotFoundException;
import com.ulger.guava.parceldeliveryservice.api.parcel.DefaultParcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.State;
import com.ulger.guava.parceldeliveryservice.api.parcel.Status;
import com.ulger.guava.parceldeliveryservice.api.parcel.data.ParcelManager;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.DefaultAddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.consent.AddressUpdateConsentChecker;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.validation.AddressUpdateValidator;
import com.ulger.validation.ValidationException;
import com.ulger.validation.ValidationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class DefaultAddressUpdateServiceTest  {

    @Mock
    private ParcelManager parcelManager;

    @Mock
    private AddressUpdateValidator updateValidator;

    @Mock
    private AddressUpdateConsentChecker addressUpdateConsentChecker;

    private AddressUpdateService addressUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        this.addressUpdateService = new DefaultAddressUpdateService(
                parcelManager,
                updateValidator,
                addressUpdateConsentChecker
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
        Mockito.verify(updateValidator, times(1)).validate(any());

        Assertions.assertThat(thrownException.getErrors()).isNotEmpty();
        Assertions.assertThat(thrownException.getErrors().size()).isEqualTo(2);
        Assertions.assertThat(thrownException.getErrors()).containsAll(Set.of("errorX", "errorY"));
    }

    @Test
    public void test_update_throws_exception_when_parcel_not_found() {
        // given
        AddressUpdateDto addressUpdateDto = AddressUpdateDto.buildWithParcelId(2L);

        Mockito
                .when(updateValidator.validate(any()))
                .thenReturn(new ValidationResult());

        Mockito
                .when(parcelManager.findById(2L))
                .thenReturn(Optional.empty());

        // when
        ResourceNotFoundException thrownException = Assertions.catchThrowableOfType(
                () -> addressUpdateService.update(addressUpdateDto),
                ResourceNotFoundException.class
        );

        // then
        InOrder inOrder = Mockito.inOrder(updateValidator, parcelManager);
        inOrder.verify(updateValidator, times(1)).validate(any());
        inOrder.verify(parcelManager, times(1)).findById(eq(2L));

        Assertions.assertThat(thrownException.getKey()).isEqualTo("error.parcel.not-found");
    }

    @Test
    public void test_update_returns_true_when_updated_successfully() {
        // given
        AddressUpdateDto addressUpdateDto = createAddressUpdateDtoSample();
        Parcel parcel = createParcelSample();

        ArgumentCaptor<AddressUpdateDto> updateDtoArgumentCaptorForValidator = ArgumentCaptor.forClass(AddressUpdateDto.class);
        ArgumentCaptor<Parcel> existingParcelArgumentCaptor = ArgumentCaptor.forClass(Parcel.class);
        ArgumentCaptor<AddressUpdateDto> updateDtoArgumentCaptorForConsentChecker = ArgumentCaptor.forClass(AddressUpdateDto.class);

        Mockito
                .when(updateValidator.validate(updateDtoArgumentCaptorForValidator.capture()))
                .thenReturn(new ValidationResult());

        Mockito
                .when(parcelManager.findById(2L))
                .thenReturn(Optional.ofNullable(parcel));

        Mockito
                .doNothing()
                .when(addressUpdateConsentChecker)
                .check(existingParcelArgumentCaptor.capture(), updateDtoArgumentCaptorForConsentChecker.capture());

        Mockito
                .when(parcelManager.updateDeliveryAddress(eq(2L), eq("addressY")))
                .thenReturn(true);

        // when
        boolean isUpdated = addressUpdateService.update(addressUpdateDto);

        // then
        InOrder inOrder = Mockito.inOrder(updateValidator, parcelManager, addressUpdateConsentChecker, parcelManager);
        inOrder.verify(updateValidator, times(1)).validate(any());
        inOrder.verify(parcelManager, times(1)).findById(eq(2L));
        inOrder.verify(addressUpdateConsentChecker, times(1)).check(any(), any());
        inOrder.verify(parcelManager, times(1)).updateDeliveryAddress(eq(2L), eq("addressY"));

    }

    private AddressUpdateDto createAddressUpdateDtoSample() {
        return AddressUpdateDto.builder()
                .parcelId(2L)
                .updaterUserId(3L)
                .deliveryAddress("addressY")
                .build();
    }

    private Parcel createParcelSample() {
        return DefaultParcel.builder()
                .id(2L)
                .barcode("barcodeX")
                .deliveryAddress("deliveryAddressX")
                .status(Status.CREATED)
                .state(State.ACTIVE)
                .ownerUserId(3L)
                .weightInGrams(200)
                .build();
    }
}