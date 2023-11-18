package com.example.storeReservation.reservation.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReservationStatus {

    STANDBY("대기 상태"),
    APPROVAL("승인 상태"),
    CANCELED("취소 상태");

    private final String description;
}