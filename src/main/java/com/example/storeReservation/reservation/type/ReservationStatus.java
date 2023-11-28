package com.example.storeReservation.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {

    STANDBY("대기 상태"),
    APPROVAL("승인 상태"),
    CANCELED("취소 상태"),
    USE_COMPLETED("사용 완료");

    private final String description;
}