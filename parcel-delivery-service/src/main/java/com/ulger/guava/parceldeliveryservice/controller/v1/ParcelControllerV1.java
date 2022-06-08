package com.ulger.guava.parceldeliveryservice.controller.v1;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationService;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.creation.ParcelCreationRequest;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.creation.ParcelCreationRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/api/v1/parcel")
public class ParcelControllerV1 {

    private final ParcelCreationService parcelCreationService;
    private final ParcelCreationRequestMapper defaultParcelCreationRequestMapper;

    public ParcelControllerV1(ParcelCreationService parcelCreationService, ParcelCreationRequestMapper defaultParcelCreationRequestMapper) {
        this.parcelCreationService = parcelCreationService;
        this.defaultParcelCreationRequestMapper = defaultParcelCreationRequestMapper;
    }

    @PostMapping
    public Parcel create(
            @AuthenticationPrincipal User user,
            @RequestBody ParcelCreationRequest parcelCreationRequest) {

        log.info("Parcel creation request has received, request={}, userId={}", parcelCreationRequest, user.getUsername());

        ParcelCreationDto parcelCreationDto = defaultParcelCreationRequestMapper.map(parcelCreationRequest, 1L);

        return parcelCreationService.create(parcelCreationDto);
    }
}