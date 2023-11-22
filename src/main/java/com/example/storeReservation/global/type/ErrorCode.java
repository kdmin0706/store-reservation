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

    /**
     * user error
     */
    USER_NOT_FOUND("사용자가 없습니다."),
    MANAGER_NOT_FOUND("매니저가 없습니다."),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다"),
    ALREADY_EXIST_USER("이미 가입된 회원입니다."),
    USER_AUTHORITY_NOT_MATCH("사용자가 맞지 않습니다."),

    /**
     * store error
     */
    STORE_NOT_FOUND("매장을 찾을 수 없습니다."),
    ALREADY_EXIST_STORE("이미 사용 중인 매장 이름입니다."),

    /**
     * reservation error
     */
    RESERVATION_NOT_FOUND("예약을 찾을 수 없습니다."),
    ALREADY_RESERVED("이미 예약된 시간입니다."),
    INVALID_ARRIVAL_STATE_UPDATE("도착 여부를 알 수 없습니다."),
    RESERVATION_STATUS_CHECK_ERROR("예약 상태 코드에 문제가 있습니다. 가게에 문의하세요."),
    RESERVATION_TIME_CHECK_ERROR("예약 시간에 문제가 있습니다. 가게에 문의하세요."),

    /**
     * review error
     */
    REVIEW_NOT_FOUND("리뷰를 찾을 수 없습니다."),

    ALREADY_EXIST_REVIEW("이미 리뷰가 존재합니다."),
    REVIEW_NOT_AVAILABLE("해당 예약은 리뷰를 쓸 수 있는 상태가 아닙니다."),

    /**
     * security error
     */
    TOKEN_TIME_OUT("토큰이 만료되었습니다."),
    JWT_TOKEN_WRONG_TYPE("JWT 토큰 형식에 문제가 있습니다.")

    ;

    private final String description;
}
