package com.example.storeReservation.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class RegisterCustomer {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public RegisterCustomer from(CustomerDto customerDto) {
        return RegisterCustomer.builder()
                .username(customerDto.getUsername())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
    }
}
