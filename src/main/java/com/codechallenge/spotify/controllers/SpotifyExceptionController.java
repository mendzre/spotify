package com.codechallenge.spotify.controllers;

import com.codechallenge.spotify.exceptions.AccessTokenNotSetException;
import com.codechallenge.spotify.models.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class SpotifyExceptionController {

    @ExceptionHandler({
            AccessTokenNotSetException.class,
            HttpClientErrorException.Unauthorized.class
    })
    public ResponseEntity<ErrorResponse> handleAccessTokenExceptions(RuntimeException exception, HttpServletRequest request) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), statusCode.getReasonPhrase(), "The access token failed", request.getRequestURI());
        return new ResponseEntity<>(errorResponse, statusCode);
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorResponse> handleJsonProcessingException(JsonProcessingException exception, HttpServletRequest request) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), statusCode.getReasonPhrase(), "Failed to transform data from the external service", request.getRequestURI());
        return new ResponseEntity<>(errorResponse, statusCode);
    }
}
