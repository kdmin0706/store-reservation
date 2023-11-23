package com.example.storeReservation.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class RegisterUser {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public RegisterUser from(UserDto userDto) {
        return RegisterUser.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }
}
