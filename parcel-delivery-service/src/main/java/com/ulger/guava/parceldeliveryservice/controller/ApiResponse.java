package com.ulger.guava.parceldeliveryservice.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class ApiResponse {

    private String requestId;
    private String message;
    private Set<String> errors;
}