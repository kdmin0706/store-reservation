package com.example.store.manager.service;

import com.example.store.manager.dto.ManagerDto;
import com.example.store.manager.dto.RegisterManager;

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
