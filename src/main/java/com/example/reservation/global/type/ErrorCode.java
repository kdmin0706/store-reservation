package com.example.reservation.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),

    USER_NOT_FOUND("사용자가 없습니다."),
    STORE_NOT_FOUND("매장을 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다"),

    ALREADY_EXISTED_STORE("이미 사용 중인 매장 이름입니다."),
    ALREADY_EXISTED_USER("이미 가입된 회원입니다.")

    ;

    private final String description;
}
