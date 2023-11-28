package com.example.storeReservation.global.exception;


import com.example.storeReservation.global.dto.ErrorResponse;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.storeReservation.global.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.example.storeReservation.global.type.ErrorCode.INVALID_REQUEST;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ErrorResponse customExceptionHandler(CustomException e) {
        log.error("{} is occurred.", e.getErrorCode());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException is occurred.", e);
        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler(Exception e) {
        log.error("Exception is occurred.", e);
        return new ErrorResponse(INTERNAL_SERVER_ERROR,
                INTERNAL_SERVER_ERROR.getDescription());
    }
}
