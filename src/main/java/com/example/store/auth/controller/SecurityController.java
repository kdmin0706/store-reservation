package com.example.store.auth.controller;

import com.example.store.global.exception.CustomException;
import com.example.store.global.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SecurityController {
    @GetMapping("/exception/accessDenied")
    public void accessDenied() {
        log.info("INVALID_ACCESS_TOKEN - SecurityController");
        throw new CustomException(ErrorCode.INVALID_ACCESS_TOKEN);
    }

    @GetMapping("/exception/unauthorized")
    public void unAuthorized() {
        log.info("JWT_TOKEN_WRONG_TYPE - SecurityController");
        throw new CustomException(ErrorCode.JWT_TOKEN_WRONG_TYPE);
    }
}
