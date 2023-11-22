package com.example.storeReservation.auth.controller;

import com.example.storeReservation.auth.dto.LoginInput;
import com.example.storeReservation.auth.security.TokenProvider;
import com.example.storeReservation.auth.service.AuthService;
import com.example.storeReservation.manager.entity.Manager;
import com.example.storeReservation.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;

    /**
     * 매니저 로그인
     * @param request: 매니저 로그인 요청
     * @return 매니저 로그인 완료 토큰
     */
    @PostMapping("/manager/login")
    public ResponseEntity<?> managerLogin(
            @RequestBody LoginInput request
    ){
        Manager manager = this.authService.authenticateManager(request);
        return ResponseEntity.ok(
                this.tokenProvider.createToken(
                        manager.getEmail(),
                        manager.getMemberType())
        );
    }

    /**
     * 유저 로그인
     * @param request: 유저 로그인 요청
     * @return 유저 로그인 완료 토큰
     */
    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(
            @RequestBody LoginInput request
    ){
        User user = this.authService.authenticateUser(request);
        return ResponseEntity.ok(
                this.tokenProvider.createToken(
                        user.getEmail(),
                        user.getMemberType())
        );
    }
}
