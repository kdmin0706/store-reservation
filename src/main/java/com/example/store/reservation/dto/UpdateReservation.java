package com.example.store.reservation.dto;

import com.example.store.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateReservation {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private ReservationStatus reservationStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reservationId;
        private String username;
        private String storeName;
        private ReservationStatus reservationStatus;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response from(ReservationDto reservationDto) {
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .username(reservationDto.getUsername())
                    .storeName(reservationDto.getStoreName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();
        }
    }
}
