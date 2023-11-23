package com.example.storeReservation.user.service;

import com.example.storeReservation.user.dto.RegisterUser;
import com.example.storeReservation.user.dto.UserDto;

public interface UserService {

    /**
     * 회원 가입 진행
     */
    UserDto register(RegisterUser user);

    /**
     * 회원 정보 확인
     */
    UserDto MemberDetail(Long userId);
}
