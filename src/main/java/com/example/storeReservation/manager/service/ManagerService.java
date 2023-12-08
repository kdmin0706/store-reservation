package com.example.storeReservation.manager.service;

import com.example.storeReservation.manager.dto.ManagerDto;
import com.example.storeReservation.manager.dto.RegisterManager;

public interface ManagerService {

    /**
     * 회원 가입 진행
     */
    ManagerDto register(RegisterManager user);

    /**
     * 회원 정보 확인
     */
    ManagerDto memberDetail(Long userId);
}
