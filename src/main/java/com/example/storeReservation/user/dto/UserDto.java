package com.example.storeReservation.user.dto;

import com.example.storeReservation.auth.type.MemberType;
import com.example.storeReservation.user.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private MemberType memberType;
    private String email;
    private String password;
    private String phoneNumber;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .memberType(user.getMemberType())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
