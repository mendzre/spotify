package com.codechallenge.spotify.controllers;

import com.codechallenge.spotify.exceptions.ResourceNotFoundException;
import com.codechallenge.spotify.models.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception, HttpServletRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), statusCode.getReasonPhrase(), "The parameter " + exception.getParameterName() + " is required", request.getRequestURI());
        return new ResponseEntity<>(errorResponse, statusCode);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(statusCode.value(), statusCode.getReasonPhrase(), exception.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, statusCode);
    }
}
