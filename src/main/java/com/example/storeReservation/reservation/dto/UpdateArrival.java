package com.example.storeReservation.reservation.dto;

import com.example.storeReservation.reservation.type.ArrivalStatus;
import com.example.storeReservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class UpdateArrival {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String username;
        private String phoneNumber;
        private LocalDateTime arrivalTime;
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
        private ArrivalStatus arrivalStatus;

        public static Response from(ReservationDto reservationDto) {
            return Response.builder()
                    .reservationId(reservationDto.getReservationId())
                    .username(reservationDto.getUsername())
                    .storeName(reservationDto.getStoreName())
                    .reservationStatus(reservationDto.getReservationStatus())
                    .arrivalStatus(reservationDto.getArrivalStatus())
                    .build();
        }
    }
}
