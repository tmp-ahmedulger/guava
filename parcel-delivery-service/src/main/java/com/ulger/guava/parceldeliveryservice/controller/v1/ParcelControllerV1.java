package com.ulger.guava.parceldeliveryservice.controller.v1;

import com.ulger.guava.parceldeliveryservice.authentication.SecurityContextHelper;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.ParcelAddressUpdateDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.address.ParcelAddressUpdateService;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.update.status.ParcelLoadingStatusUpdateService;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.creation.ParcelCreationRequest;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.creation.ParcelCreationRequestMapper;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.update.ParcelAddressUpdateRequest;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.update.ParcelLoadingStatusUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest/api/v1/parcel")
public class ParcelControllerV1 {

    private final ParcelCreationService parcelCreationService;
    private final ParcelAddressUpdateService parcelAddressUpdateService;
    private final ParcelLoadingStatusUpdateService parcelLoadingStatusUpdateService;
    private final ParcelCreationRequestMapper defaultParcelCreationRequestMapper;

    public ParcelControllerV1(
            ParcelCreationService parcelCreationService,
            ParcelAddressUpdateService parcelAddressUpdateService,
            ParcelLoadingStatusUpdateService parcelLoadingStatusUpdateService,
            ParcelCreationRequestMapper defaultParcelCreationRequestMapper) {

        this.parcelCreationService = parcelCreationService;
        this.parcelAddressUpdateService = parcelAddressUpdateService;
        this.parcelLoadingStatusUpdateService = parcelLoadingStatusUpdateService;
        this.defaultParcelCreationRequestMapper = defaultParcelCreationRequestMapper;
    }

    @PostMapping
    public Parcel create(@RequestBody ParcelCreationRequest creationRequest) {

        Long userId = SecurityContextHelper.getAuthenticated().getUserId();

        log.info("Parcel creation request has received, request={}, userId={}", creationRequest, userId);
        ParcelCreationDto parcelCreationDto = defaultParcelCreationRequestMapper.map(creationRequest, userId);

        return parcelCreationService.create(parcelCreationDto);
    }

    @PutMapping("/{parcelId}/address")
    public ResponseEntity<Void> updateAddress(
            @RequestBody ParcelAddressUpdateRequest updateRequest,
            @PathVariable("parcelId") Long parcelId) {

        Long userId = SecurityContextHelper.getAuthenticated().getUserId();

        log.info("Parcel address update request received, parcelId={}, userId={}, address={}",
                parcelId, userId, updateRequest.getDeliveryAddress());

        ParcelAddressUpdateDto addressUpdateDto = ParcelAddressUpdateDto.builder()
                .userId(userId)
                .parcelId(parcelId)
                .deliveryAddress(updateRequest.getDeliveryAddress())
                .build();

        parcelAddressUpdateService.update(addressUpdateDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{parcelId}/loading-status")
    public ResponseEntity<Void> updateLoadingStatus(
            @RequestBody ParcelLoadingStatusUpdateRequest updateRequest,
            @PathVariable("parcelId") Long parcelId) {

        Long userId = SecurityContextHelper.getAuthenticated().getUserId();

        log.info("Parcel status update request received, parcelId={}, userId={}, status={}",
                parcelId, userId, updateRequest.getLoadingStatus());

        parcelLoadingStatusUpdateService.update(parcelId, userId, updateRequest.getLoadingStatus());

        return ResponseEntity.noContent().build();
    }
}