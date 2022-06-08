package com.ulger.cloud.authenticationserver.rest;

import com.ulger.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ValidationException.class })
    protected ResponseEntity<Object> handleBadRequestException(ValidationException e) {
        return ResponseEntity
                .badRequest()
                .body(
                        BaseResponse
                                .builder()
                                .message(e.getMessage())
                                .errors(e.getErrors())
                                .build()
                );
    }
}