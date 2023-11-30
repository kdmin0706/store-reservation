package com.example.storeReservation.customer.controller;

import com.example.storeReservation.customer.dto.RegisterCustomer;
import com.example.storeReservation.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 회원 가입
     * @param request : 회원 가입
     * @return register 정보
     */
    @PostMapping("/register/customer")
    public ResponseEntity<?> register(@RequestBody RegisterCustomer request) {
        return ResponseEntity.ok().body(
                request.from(this.customerService.register(request)));
    }

    /**
     * 회원 본인 정보 조회 (사용자 본인 및 매니저 모두 확인 가능)
     * @param id 사용자 아이디
     * @return 사용자 정보
     */
    @GetMapping("/customer/info")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> getCustomerInfo(@RequestParam("id") Long id) {
        return ResponseEntity.ok(this.customerService.MemberDetail(id));
    }
}
