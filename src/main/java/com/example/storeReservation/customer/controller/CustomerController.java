package com.example.storeReservation.customer.controller;

import com.example.storeReservation.customer.dto.RegisterCustomer;
import com.example.storeReservation.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 회원 가입
     * @param request : 회원 가입
     * @return register 정보
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterCustomer request) {
        return ResponseEntity.ok().body(
                request.from(this.customerService.register(request)));
    }

    /**
     * 회원 정보 조회
     * @param id 사용자 아이디
     * @return 사용자 정보
     */
    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestParam("id") Long id) {
        return ResponseEntity.ok(this.customerService.MemberDetail(id));
    }
}
