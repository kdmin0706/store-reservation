package com.example.reservation.global.exception;


import com.example.reservation.global.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

import static com.example.reservation.global.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.reservation.global.type.ErrorCode.INVALID_ACCESS;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandler(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getErrorCode(), e.getErrorMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedExceptionHandler(AccessDeniedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                INVALID_ACCESS, INVALID_ACCESS.getDescription());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler() {
        ErrorResponse errorResponse = new ErrorResponse(
                INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getDescription());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
