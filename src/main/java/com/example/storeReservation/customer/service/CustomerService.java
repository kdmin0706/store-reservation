package com.example.storeReservation.customer.service;

import com.example.storeReservation.customer.dto.RegisterCustomer;
import com.example.storeReservation.customer.dto.CustomerDto;

public interface CustomerService {

    /**
     * 회원 가입 진행
     */
    CustomerDto register(RegisterCustomer user);

    /**
     * 회원 정보 확인
     */
    CustomerDto MemberDetail(Long userId);
}
