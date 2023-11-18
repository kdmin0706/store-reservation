package com.example.storeReservation.member.entity;

import com.example.storeReservation.global.entity.BaseEntity;
import com.example.storeReservation.member.Model.type.MemberStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    /**
     * 회원 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 회원 이름
     */
    @NotBlank
    private String username;

    /**
     * 회원 구분
     */
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    /**
     * 회원 이메일
     */
    @NotBlank
    @Column(unique = true)
    private String email;

    /**
     * 회원 비밀번호
     */
    @NotBlank
    private String password;

    /**
     * 회원 휴대폰 번호
     */
    @NotBlank
    private String phoneNumber;

}
