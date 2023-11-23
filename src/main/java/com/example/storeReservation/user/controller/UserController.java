package com.example.storeReservation.user.controller;

import com.example.storeReservation.user.dto.RegisterUser;
import com.example.storeReservation.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입
     * @param request : 회원 가입
     * @return register 정보
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUser request) {
        return ResponseEntity.ok().body(
                request.from(this.userService.register(request)));
    }

    /**
     * 회원 정보 조회
     * @param id 사용자 아이디
     * @return 사용자 정보
     */
    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestParam("id") Long id) {
        return ResponseEntity.ok(this.userService.MemberDetail(id));
    }
}
