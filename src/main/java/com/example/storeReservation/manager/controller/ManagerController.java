package com.example.storeReservation.manager.controller;

import com.example.storeReservation.manager.dto.RegisterManager;
import com.example.storeReservation.manager.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 회원 가입
     * @param request : 회원 가입
     * @return register 정보
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterManager request
    ) {
        return ResponseEntity.ok().body(
                request.from(this.managerService.register(request)));
    }

    /**
     * 회원 정보 조회
     * @param managerId 사용자 아이디
     * @return 사용자 정보
     */
    @GetMapping
    public ResponseEntity<?> getUserInfo(
            @RequestParam("managerid") Long managerId
    ) {
        return ResponseEntity.ok(
                this.managerService.MemberDetail(managerId)
        );
    }
}
