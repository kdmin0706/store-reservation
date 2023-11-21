package com.example.storeReservation.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class LoginInput {
    private String email;
    private String password;
}
