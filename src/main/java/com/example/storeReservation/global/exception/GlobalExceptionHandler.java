package com.example.storeReservation.global.exception;


import com.example.storeReservation.global.model.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.storeReservation.global.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.storeReservation.global.type.ErrorCode.INVALID_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ErrorResponse customExceptionHandler(CustomException e) {
        return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleADataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler() {
        return new ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getDescription());
    }
}
