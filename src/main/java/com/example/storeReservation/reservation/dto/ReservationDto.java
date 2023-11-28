package com.example.storeReservation.reservation.dto;

import com.example.storeReservation.reservation.entity.Reservation;
import com.example.storeReservation.reservation.type.ArrivalStatus;
import com.example.storeReservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReservationDto {
    private Long reservationId;
    private String username;
    private String userPhoneNumber;
    private String storeName;

    private ReservationStatus reservationStatus;
    private ArrivalStatus arrivalStatus;

    private LocalDate reservationDate;
    private LocalTime reservationTime;

    public static ReservationDto fromEntity(Reservation reservation) {
        return ReservationDto.builder()
                .reservationId(reservation.getId())
                .username(reservation.getCustomer().getUsername())
                .userPhoneNumber(reservation.getCustomer().getPhoneNumber())
                .storeName(reservation.getStore().getStoreName())
                .reservationStatus(reservation.getReservationStatus())
                .arrivalStatus(reservation.getArrivalStatus())
                .reservationDate(reservation.getReservationDate())
                .reservationTime(reservation.getReservationTime())
                .build();
    }
}
