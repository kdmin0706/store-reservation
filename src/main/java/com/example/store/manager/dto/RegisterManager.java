package com.example.store.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class RegisterManager {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public RegisterManager from(ManagerDto managerDto) {
        return RegisterManager.builder()
                .username(managerDto.getUsername())
                .email(managerDto.getEmail())
                .password(managerDto.getPassword())
                .phoneNumber(managerDto.getPhoneNumber())
                .build();
    }
}
