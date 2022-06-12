package com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1;

import com.ulger.guava.parceldeliveryservice.api.parcel.Parcel;
import com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.creation.ParcelCreationRequest;
import com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.update.ParcelAddressUpdateRequest;
import com.ulger.guava.parceldeliveryservice.infrastructure.controller.v1.request.update.ParcelStatusUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;

public interface ParcelControllerV1 {

    @Tag(name = "Parcel Creating")
    @Consumes("application/json")
    @Operation(
            summary = "Creates parcel",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Parcel Object", content = @Content(mediaType = "application/json", schema = @Schema(implementation=Parcel.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid data"),
                    @ApiResponse(responseCode = "401", description = "When credentials not given")
            })
    Parcel create(
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "Bearer Token Starts with JWT")
            @RequestBody(
                    description = "Parcel Creation Request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ParcelCreationRequest.class))) ParcelCreationRequest creationRequest);

    @Tag(name = "Parcel Address Updating")
    @Consumes("application/json")
    @Operation(
            summary = "Updates parcel address",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Nothing returns"),
                    @ApiResponse(responseCode = "400", description = "Invalid data"),
                    @ApiResponse(responseCode = "401", description = "When credentials not given"),
                    @ApiResponse(responseCode = "403", description = "When operation not permitted to attempting user"),
                    @ApiResponse(responseCode = "404", description = "When parcel not found with given id")
            })
    ResponseEntity<?> updateAddress(
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "Bearer Token Starts with JWT")
            @RequestBody(
                    description = "Parcel Address Update Request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ParcelAddressUpdateRequest.class))) ParcelAddressUpdateRequest updateRequest,
            @Parameter(description = "Parcel Id", required = true) Long parcelId);

    @Tag(name = "Parcel Status Updating")
    @Consumes("application/json")
    @Operation(
            summary = "Updates parcel status",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Nothing returns"),
                    @ApiResponse(responseCode = "400", description = "Invalid data"),
                    @ApiResponse(responseCode = "401", description = "When credentials not given"),
                    @ApiResponse(responseCode = "403", description = "When operation not permitted to attempting user"),
                    @ApiResponse(responseCode = "404", description = "When parcel not found with given id")
            })
    ResponseEntity<?> updateStatus(
            @Parameter(in = ParameterIn.HEADER, name = "Authorization", description = "Bearer Token Starts with JWT")
            @RequestBody(
                    description = "Parcel Status Update Request",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ParcelStatusUpdateRequest.class))) ParcelStatusUpdateRequest updateRequest,
            @Parameter(description = "Parcel Id", required = true) Long parcelId);
}
