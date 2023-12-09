package com.example.store.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrivalStatus {

    READY("대기 상태"),
    ARRIVED("도착 완료"),
    NO_SHOW("노쇼");

    private final String description;
}