package com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.AddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.StatusUpdateService;
import com.ulger.guava.parceldeliveryservice.infrastructure.authentication.SecurityContextHelper;
import com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.creation.ParcelCreationRequest;
import com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.creation.ParcelCreationRequestMapper;
import com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.update.ParcelAddressUpdateRequest;
import com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.update.ParcelStatusUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest/api/v1/parcel")
public class ParcelControllerV1 {

    private final ParcelCreationService parcelCreationService;
    private final AddressUpdateService addressUpdateService;
    private final StatusUpdateService statusUpdateService;
    private final ParcelCreationRequestMapper defaultParcelCreationRequestMapper;

    public ParcelControllerV1(
            ParcelCreationService parcelCreationService,
            AddressUpdateService addressUpdateService,
            StatusUpdateService statusUpdateService,
            ParcelCreationRequestMapper defaultParcelCreationRequestMapper) {

        this.parcelCreationService = parcelCreationService;
        this.addressUpdateService = addressUpdateService;
        this.statusUpdateService = statusUpdateService;
        this.defaultParcelCreationRequestMapper = defaultParcelCreationRequestMapper;
    }

    @PostMapping
    public Parcel create(@RequestBody ParcelCreationRequest creationRequest) {

        Long userId = SecurityContextHelper.getAuthenticated().getUserId();

        log.info("Parcel creation request has received, request={}, userId={}", creationRequest, userId);
        ParcelCreationDto parcelCreationDto = defaultParcelCreationRequestMapper.map(creationRequest, userId);

        return parcelCreationService.create(parcelCreationDto);
    }

    @PreAuthorize("hasAuthority('STANDARD') or hasAuthority('ADMIN')")
    @PutMapping("/{parcelId}/address")
    public ResponseEntity<?> updateAddress(
            @RequestBody ParcelAddressUpdateRequest updateRequest,
            @PathVariable("parcelId") Long parcelId) {

        Long userId = SecurityContextHelper.getAuthenticated().getUserId();

        log.info("Parcel address update request received, parcelId={}, userId={}, address={}",
                parcelId, userId, updateRequest.getDeliveryAddress());

        AddressUpdateDto addressUpdateDto = AddressUpdateDto.builder()
                .updaterUserId(userId)
                .parcelId(parcelId)
                .deliveryAddress(updateRequest.getDeliveryAddress())
                .build();

        boolean isUpdated = addressUpdateService.update(addressUpdateDto);

        if (!isUpdated) {
            return ResponseEntity
                    .internalServerError()
                    .body("Parcel address can not updated. Contact with administrators");
        }

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('COURIER') or hasAuthority('ADMIN')")
    @PutMapping("/{parcelId}/status")
    public ResponseEntity<?> updateStatus(
            @RequestBody ParcelStatusUpdateRequest updateRequest,
            @PathVariable("parcelId") Long parcelId) {

        Long userId = SecurityContextHelper.getAuthenticated().getUserId();

        log.info("Parcel status update request received, parcelId={}, userId={}, statusCode={}",
                parcelId, userId, updateRequest.getStatusCode());

        StatusUpdateDto statusUpdateDto = StatusUpdateDto.builder()
                .updaterUserId(userId)
                .parcelId(parcelId)
                .statusCode(updateRequest.getStatusCode())
                .build();

        boolean isUpdated = statusUpdateService.update(statusUpdateDto);

        if (!isUpdated) {
            return ResponseEntity
                    .internalServerError()
                    .body("Parcel status can not updated. Contact with administrators");
        }

        return ResponseEntity.noContent().build();
    }
}