package com.ulger.guava.parceldeliveryservice.controller.v1;

import antlr.TokenStream;
import com.ulger.guava.parceldeliveryservice.api.authentication.SecurityContextHelper;
import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationDto;
import com.ulger.guava.parceldeliveryservice.api.parcel.operation.creation.ParcelCreationService;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.creation.ParcelCreationRequest;
import com.ulger.guava.parceldeliveryservice.controller.v1.request.creation.ParcelCreationRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @Autowired
    private TokenStore tokenStore;

    @PostMapping
    public Parcel create(@RequestBody ParcelCreationRequest parcelCreationRequest) {

        Long userId = SecurityContextHelper
                .getAuthentication()
                .get()
                .getUserId();

        log.info("Parcel creation request has received, request={}, userId={}", parcelCreationRequest, userId);

        ParcelCreationDto parcelCreationDto = defaultParcelCreationRequestMapper.map(parcelCreationRequest, 1L);

        return parcelCreationService.create(parcelCreationDto);
    }

    private Map<String, Object> getAdditionalInfo(Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation();
    }
}