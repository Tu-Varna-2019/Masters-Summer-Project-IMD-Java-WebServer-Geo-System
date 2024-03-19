package com.tuvarna.geo.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tuvarna.geo.exception.AccessDeniedError;
import com.tuvarna.geo.exception.BadRequestError;
import com.tuvarna.geo.exception.ResourceNotFoundError;
import com.tuvarna.geo.service.dto.RestApiResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundError.class)
    public ResponseEntity<RestApiResponse<Void>> handleResourceNotFoundError(ResourceNotFoundError ex,
            WebRequest request) {
        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestError.class)
    public ResponseEntity<RestApiResponse<Void>> handleBadRequestError(BadRequestError ex, WebRequest request) {

        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(),
                HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedError.class)
    public ResponseEntity<RestApiResponse<Void>> handleAccessDeniedError(AccessDeniedError ex, WebRequest request) {
        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, ex.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestApiResponse<Void>> handleGlobalException(Exception ex, WebRequest request) {
        RestApiResponse<Void> apiResponse = new RestApiResponse<>(null, "An error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}