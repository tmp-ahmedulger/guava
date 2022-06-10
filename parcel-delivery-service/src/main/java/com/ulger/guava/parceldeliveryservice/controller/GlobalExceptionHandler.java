package com.ulger.guava.parceldeliveryservice.controller;

import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.service.MessageService;
import com.ulger.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageService messageService;

    public GlobalExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception) {

        if (exception instanceof ApiException) {
            return handleApiException((ApiException) exception);
        }

        if (exception instanceof ValidationException) {
            return handleValidationException((ValidationException) exception);
        }

        return handleExceptionInternal(exception);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex) {
        log.error("Unknown Exception occurred", ex);

        ApiResponse apiErrorResponse = ApiResponse.builder()
                .message(("Your request could not proceed"))
                .build();

        // All API errors are handled with 400. Because they are expected cases and missing or invalid parameters
        return ResponseEntity
                .internalServerError()
                .body(apiErrorResponse);
    }

    protected ResponseEntity<Object> handleApiException(ApiException exception) {
        log.error("API Exception occurred, reason: {}", exception.getKey(), exception);

        String resolvedMessage = messageService.getMessage(exception.getKey(), exception.getArgs());

        log.error("API Exception detail: {}", resolvedMessage);

        ApiResponse apiErrorResponse = ApiResponse.builder()
                .message(resolvedMessage)
                .build();

        // All API errors are handled with 400. Because they are expected cases and missing or invalid parameters
        return ResponseEntity
                .badRequest()
                .body(apiErrorResponse);
    }

    protected ResponseEntity<Object> handleValidationException(ValidationException exception) {
        ApiResponse apiErrorResponse = ApiResponse.builder()
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();

        return ResponseEntity
                .badRequest()
                .body(apiErrorResponse);
    }
}