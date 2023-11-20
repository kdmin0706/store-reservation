package com.example.storeReservation.manager.dto;

import com.example.storeReservation.auth.type.MemberType;
import com.example.storeReservation.manager.entity.Manager;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    private Long id;
    private String username;
    private MemberType memberType;
    private String email;
    private String password;
    private String phoneNumber;

    public static ManagerDto fromEntity(Manager manager) {
        return ManagerDto.builder()
                .id(manager.getId())
                .username(manager.getUsername())
                .memberType(manager.getMemberType())
                .email(manager.getEmail())
                .password(manager.getPassword())
                .phoneNumber(manager.getPhoneNumber())
                .build();
    }
}
