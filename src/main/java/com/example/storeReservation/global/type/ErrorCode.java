package com.example.storeReservation.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * common error
     */
    INTERNAL_SERVER_ERROR("내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다."),
    INVALID_ACCESS("유효하지 않은 접근입니다."),

    /**
     * user error
     */
    USER_NOT_FOUND("사용자가 없습니다."),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다"),
    ALREADY_EXISTED_USER("이미 가입된 회원입니다."),

    /**
     * store error
     */
    STORE_NOT_FOUND("매장을 찾을 수 없습니다."),
    ALREADY_EXISTED_STORE("이미 사용 중인 매장 이름입니다.")

    ;

    private final String description;
}