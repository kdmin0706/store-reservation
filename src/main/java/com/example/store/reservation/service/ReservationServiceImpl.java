package com.example.store.reservation.service;

import com.example.store.customer.entity.Customer;
import com.example.store.customer.repository.CustomerRepository;
import com.example.store.global.exception.CustomException;
import com.example.store.manager.repository.ManagerRepository;
import com.example.store.reservation.dto.CreateReservation;
import com.example.store.reservation.dto.ReservationDto;
import com.example.store.reservation.dto.UpdateArrival;
import com.example.store.reservation.dto.UpdateReservation;
import com.example.store.reservation.entity.Reservation;
import com.example.store.reservation.repository.ReservationRepository;
import com.example.store.reservation.type.ReservationStatus;
import com.example.store.store.entity.Store;
import com.example.store.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.store.global.type.ErrorCode.*;
import static com.example.store.reservation.type.ArrivalStatus.ARRIVED;
import static com.example.store.reservation.type.ArrivalStatus.READY;
import static com.example.store.reservation.type.ReservationStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public ReservationDto createReservation(CreateReservation.Request request) {
        log.info("예약 등록 시작: {}", request.toString());

        Store store = this.storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        Customer customer = this.customerRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        LocalDateTime reserveTime = LocalDateTime.of(
                request.getReservationDate(), request.getReservationTime());

        boolean exists = this.reservationRepository.existReservationTime(reserveTime);
        if (exists) {
            log.info(ALREADY_RESERVED.getDescription());
            throw new CustomException(ALREADY_RESERVED);
        }

        Reservation reservation = this.reservationRepository.save(Reservation.builder()
                .customer(customer)
                .store(store)
                .reservationStatus(STANDBY)
                .arrivalStatus(READY)
                .reservationDate(request.getReservationDate())
                .reservationTime(request.getReservationTime())
                .build());

        log.info("예약 등록 완료");
        return ReservationDto.fromEntity(reservation);
    }

    @Override
    @Transactional
    public ReservationDto updateReservation(Long reservationId, UpdateReservation.Request request) {
        log.info("예약 승인 여부 변경");

        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        ReservationStatus status = reservation.getReservationStatus();
        if (status.equals(request.getReservationStatus())) {
            log.info(RESERVATION_STATUS_CHECK_ERROR.getDescription());
            throw new CustomException(RESERVATION_STATUS_CHECK_ERROR);
        }

        reservation.setReservationStatus(request.getReservationStatus());
        log.info("예약 승인 여부 변경 완료");

        return ReservationDto.fromEntity(
                this.reservationRepository.save(reservation));
    }

    @Override
    public List<Reservation> searchReservation(Long id) {
        log.info("예약 요청 목록 조회");

        List<Reservation> reservations
                = this.reservationRepository.findAllByManagerReservation(id);

        if (reservations.isEmpty()) {
            log.info(RESERVATION_NOT_FOUND.getDescription());
            throw new CustomException(RESERVATION_NOT_FOUND);
        }

        return reservations;
    }

    @Override
    @Transactional
    public ReservationDto updateArrival(Long reservationId, UpdateArrival.Request request) {
        log.info("예약자 도착 여부 변경 시작");
        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validationReservation(reservation, request.getArrivalTime().toLocalTime());

        reservation.setArrivalStatus(ARRIVED);
        reservation.setReservationStatus(USE_COMPLETED);

        log.info("예약자 도착 여부 변경 완료");

        return ReservationDto.fromEntity(
                this.reservationRepository.save(reservation));
    }

    @Override
    @Transactional
    public ReservationDto cancelReservation(Long reservationId) {
        log.info("예약 상태 취소 시작");

        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        reservation.setReservationStatus(CANCELED);
        log.info("예약 상태 취소 완료");

        return ReservationDto.fromEntity(
                this.reservationRepository.save(reservation));
    }

    /**
     * 예약 관련 유효성 검사
     * 1. 예약 상태가 승인(approval) 확인
     * 2. 예약 시간이 지났을 경우
     * 3. 예약 10분 전 도착 확인
     */
    private void validationReservation(Reservation reservation, LocalTime arrivalTime) {
        if (!reservation.getReservationStatus().equals(ReservationStatus.APPROVAL)) {
            throw new CustomException(RESERVATION_STATUS_CHECK_ERROR);
        } else if (arrivalTime.isAfter(reservation.getReservationTime())) {
            throw new CustomException(RESERVATION_TIME_EXCEEDED);
        } else if (arrivalTime.isBefore(reservation.getReservationTime().minusMinutes(10L))) {
            throw new CustomException(CHECK_IT_10_MINUTES_BEFORE_THE_RESERVATION_TIME);
        }
    }
}
