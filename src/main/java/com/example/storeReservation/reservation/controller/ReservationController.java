package com.example.storeReservation.reservation.controller;

import com.example.storeReservation.reservation.dto.CreateReservation;
import com.example.storeReservation.reservation.dto.SearchReservation;
import com.example.storeReservation.reservation.dto.UpdateArrival;
import com.example.storeReservation.reservation.dto.UpdateReservation;
import com.example.storeReservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * 매장 예약 진행 시스템
     * @param request : 예약에 필요한 정보
     * @return 예약 정보
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReservation.Response createReservation(
            @RequestBody CreateReservation.Request request
    ) {
        return CreateReservation.Response.from(
                this.reservationService.createReservation(request)
        );
    }

    /**
     * 예약 승인 및 취소 진행
     * @param request : 기존 예약 정보
     * @return : 예약 변경
     */
    @PutMapping("/partner/approval/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateReservation.Response updateReservation(
            @PathVariable Long id,
            @RequestBody UpdateReservation.Request request
    ) {
        return UpdateReservation.Response.from(
                this.reservationService.updateReservation(id, request)
        );
    }

    /**
     * 예약 리스트
     * @param id: 매니저 아이디
     * @return 매장 예약 리스트
     */
    @GetMapping("/partner/reservation-list/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public SearchReservation getReservationList(@PathVariable Long id) {
        return SearchReservation.from(
                this.reservationService.searchReservation(id));
    }

    /**
     * 매장 도착 확인 여부 변경
     * @param id: 예약 아이디
     * @param request: 예약한 유저 정보
     * @return : 예약 완료 데이터
     */
    @PutMapping("/kiosk/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateArrival.Response updateArrivalKiosk(
            @PathVariable Long id,
            @RequestBody UpdateArrival.Request request
    ) {
        return UpdateArrival.Response.from(
                this.reservationService.updateArrival(id, request)
        );
    }

    /**
     * 예약 취소
     * @param reservationId : 예약 아이디
     * @return 예약 취소 데이터
     */
    @PutMapping("/cancel")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> cancelReservation(
            @RequestParam(name = "reservationid") Long reservationId
    ) {
        return ResponseEntity.ok(
                this.reservationService.cancelReservation(reservationId));
    }
}