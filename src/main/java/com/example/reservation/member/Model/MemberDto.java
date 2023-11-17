package com.example.reservation.member.Model;

import com.example.reservation.member.Model.type.UserType;
import com.example.reservation.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private UserType userType;
    private String email;
    private String password;
    private String phoneNumber;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .userType(member.getUserType())
                .email(member.getEmail())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
