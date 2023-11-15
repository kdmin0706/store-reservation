package com.example.reservation.global.exception;


import com.example.reservation.global.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.reservation.global.type.ErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler() {
        ErrorResponse errorResponse = new ErrorResponse(
                INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getDescription());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
