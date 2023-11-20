package com.example.storeReservation.auth.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberType {
    /**
     * 일반 회원
     */
    USER("ROLE_USER"),

    /**
     * 매니저
     */
    MANAGER("ROLE_MANAGER");

    private final String description;
}
