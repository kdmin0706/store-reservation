package com.example.storeReservation.reservation.dto;

import com.example.storeReservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservation {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private Long userId;

        @NotNull
        private Long storeId;

        private LocalDate reservationDate;
        private LocalTime reservationTime;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String username;
        private String userPhoneNumber;
        private String storeName;
        private ReservationStatus reservationStatus;

        private LocalDate reservationDate;
        private LocalTime reservationTime;

        public static Response from(ReservationDto reservationDto) {
            return Response.builder()
                    .username(reservationDto.getUsername())
                    .userPhoneNumber(reservationDto.getUserPhoneNumber())
                    .storeName(reservationDto.getStoreName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .reservationDate(reservationDto.getReservationDate())
                    .reservationTime(reservationDto.getReservationTime())
                    .build();
        }
    }
}
