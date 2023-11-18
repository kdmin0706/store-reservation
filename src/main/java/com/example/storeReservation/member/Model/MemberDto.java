package com.example.storeReservation.member.Model;

import com.example.storeReservation.member.Model.type.MemberStatus;
import com.example.storeReservation.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String username;
    private MemberStatus memberStatus;
    private String email;
    private String password;
    private String phoneNumber;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .memberStatus(member.getMemberStatus())
                .email(member.getEmail())
                .password(member.getPassword())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
