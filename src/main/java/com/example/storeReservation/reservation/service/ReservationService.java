package com.example.storeReservation.reservation.service;

import com.example.storeReservation.reservation.dto.CreateReservation;
import com.example.storeReservation.reservation.dto.ReservationDto;
import com.example.storeReservation.reservation.dto.UpdateArrival;
import com.example.storeReservation.reservation.dto.UpdateReservation;
import com.example.storeReservation.reservation.entity.Reservation;

import java.util.List;

public interface ReservationService {

    /**
     * 예약 등록 서비스
     *
     * @param request 예약 등록시 필요한 데이터 요청
     * @return 예약 등록 확인 완료 데이터
     */
    ReservationDto createReservation(CreateReservation.Request request);

    /**
     * 예약 승인 여부 변경
     * @param reservationId : 예약 아이디
     * @param request : 예약 승인 여부 확인
     * @return : 예약 정보 데이터
     */
    ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request);

    /**
     * 예약 리스트 조회 (매니저)
     * @param id : 매니저 아이디
     * @return : 예약 리스트
     */
    List<Reservation> searchReservation(Long id);

    /**
     * 도착 여부 변경
     * @param reservationId: 예약 아이디
     * @param request : 예약한 유저 정보
     * @return : 예약한 유저 도착 여부 변경
     */
    ReservationDto updateArrival(Long reservationId, UpdateArrival.Request request);

}
