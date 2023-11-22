package com.example.storeReservation.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
public class LoginInput {

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;
}
