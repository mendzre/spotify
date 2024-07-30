package com.codechallenge.spotify.controllers;

import com.codechallenge.spotify.exceptions.ResourceAlreadyExists;
import com.codechallenge.spotify.models.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrackExceptionController {
    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(ResourceAlreadyExists exception, HttpServletRequest request) {
        HttpStatus statusCode = HttpStatus.CONFLICT;
        ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), statusCode.getReasonPhrase(), exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, statusCode);
    }
}
