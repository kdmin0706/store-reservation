package com.example.storeReservation.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * common error
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),

    /**
     * user error
     */
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "사용자가 없습니다."),
    MANAGER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "매니저가 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다"),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST.value(), "이미 가입된 회원입니다."),
    ALREADY_LOGIN_USER(HttpStatus.BAD_REQUEST.value(), "이미 로그인이 되어 있는 상태입니다."),
    USER_AUTHORITY_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "사용자가 맞지 않습니다."),
    /**
     * store error
     */
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "매장을 찾을 수 없습니다."),
    ALREADY_EXIST_STORE(HttpStatus.BAD_REQUEST.value(), "이미 사용 중인 매장 이름입니다."),

    /**
     * reservation error
     */
    RESERVATION_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "예약을 찾을 수 없습니다."),
    ALREADY_RESERVED(HttpStatus.BAD_REQUEST.value(), "이미 예약된 시간입니다."),
    RESERVATION_STATUS_CHECK_ERROR(HttpStatus.BAD_REQUEST.value(), "예약 상태 코드에 문제가 있습니다. 가게에 문의하세요."),
    RESERVATION_TIME_EXCEEDED(HttpStatus.BAD_REQUEST.value(), "예약시간이 넘었습니다."),
    CHECK_IT_10_MINUTES_BEFORE_THE_RESERVATION_TIME(HttpStatus.BAD_REQUEST.value(), "예약시간 10분 전부터 확인 가능합니다."),

    /**
     * review error
     */
    REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "리뷰를 찾을 수 없습니다."),
    ALREADY_EXIST_REVIEW(HttpStatus.BAD_REQUEST.value(), "이미 리뷰가 존재합니다."),
    REVIEW_NOT_AVAILABLE(HttpStatus.BAD_REQUEST.value(), "해당 예약은 리뷰를 쓸 수 있는 상태가 아닙니다."),
    REVIEW_RATING_RANGE_OVER(HttpStatus.BAD_REQUEST.value(), "별점 범위를 넘어갔습니다."),
    REVIEW_TEXT_TOO_LONG(HttpStatus.BAD_REQUEST.value(), "텍스트 길이가 범위를 넘어갔습니다."),

    /**
     * security error
     */
    WRONG_TOKEN(HttpStatus.BAD_REQUEST.value(), "잘못된 토큰입니다."),
    TOKEN_TIME_OUT(HttpStatus.CONFLICT.value(), "만료된 JWT 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST.value(), "지원되지 않는 토큰입니다."),
    JWT_TOKEN_WRONG_TYPE(HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 구성의 JWT 토큰입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.FORBIDDEN.value(), "접근 권한이 없습니다."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED.value(), "로그인이 되지 않았습니다."),
    WRONG_TYPE_SIGNATURE(HttpStatus.UNAUTHORIZED.value(), "잘못된 JWT 서명입니다.")
    ;

    private final int statusCode;
    private final String description;
}
