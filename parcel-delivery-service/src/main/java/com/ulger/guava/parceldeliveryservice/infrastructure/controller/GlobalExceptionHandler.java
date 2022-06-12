package com.ulger.guava.parceldeliveryservice.infrastructure.controller;

import com.ulger.guava.parceldeliveryservice.api.ApiException;
import com.ulger.guava.parceldeliveryservice.api.PermissionException;
import com.ulger.guava.parceldeliveryservice.api.ResourceNotFoundException;
import com.ulger.guava.parceldeliveryservice.api.consent.ConsentFilterException;
import com.ulger.guava.parceldeliveryservice.infrastructure.service.MessageService;
import com.ulger.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageService messageService;

    public GlobalExceptionHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) throws Exception {

        if (e instanceof ApiException) {
            return handleApiException((ApiException) e);
        }

        if (e instanceof ValidationException) {
            return handleValidationException((ValidationException) e);
        }

        if (e instanceof ResourceNotFoundException) {
            return handleResourceNotFoundException((ResourceNotFoundException) e);
        }

        if (e instanceof ConsentFilterException && e.getCause() instanceof PermissionException) {
            return handlePermissionException((PermissionException) e.getCause());
        }

        if (e instanceof ConsentFilterException && e.getCause() instanceof ApiException) {
            return handleApiException((ApiException) e.getCause());
        }

        if (e instanceof ConsentFilterException) {
            return handleConsentFilterException((ConsentFilterException) e);
        }

        return super.handleException(e, request);
    }
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Unknown Exception occurred", e);

        return super.handleExceptionInternal(e, body, headers, status, request);
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

    private ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.notFound().build();
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

    private ResponseEntity<Object> handleConsentFilterException(ConsentFilterException e) {
        ApiResponse apiErrorResponse = ApiResponse.builder()
                .message("Your request can not processed. Contact with administrators.")
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(apiErrorResponse);
    }

    private ResponseEntity<Object> handlePermissionException(PermissionException e) {

        ApiResponse apiErrorResponse = ApiResponse.builder()
                .message("You are not permitted to do this operation")
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(apiErrorResponse);
    }
}