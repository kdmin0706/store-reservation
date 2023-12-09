package com.example.store.customer.service;

import com.example.store.customer.dto.CustomerDto;
import com.example.store.customer.dto.RegisterCustomer;

public interface CustomerService {

    /**
     * 회원 가입 진행
     */
    CustomerDto register(RegisterCustomer user);

    /**
     * 회원 정보 확인
     */
    CustomerDto memberDetail(Long userId);
}
