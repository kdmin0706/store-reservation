package com.example.storeReservation.reservation.entity;

import com.example.storeReservation.customer.entity.Customer;
import com.example.storeReservation.global.entity.BaseEntity;
import com.example.storeReservation.manager.entity.Manager;
import com.example.storeReservation.reservation.type.ArrivalStatus;
import com.example.storeReservation.reservation.type.ReservationStatus;
import com.example.storeReservation.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {
    /**
     * 예약 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 매니저 아이디
     */
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    /**
     * 유저 아이디
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * store 아이디
     */
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    /**
     * 승인 여부 확인
     */
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    /**
     * 도착 여부 확인
     */
    @Enumerated(EnumType.STRING)
    private ArrivalStatus arrivalStatus;

    /**
     * 매장 예약 날짜
     */
    private LocalDate reservationDate;

    /**
     * 매장 예약 시간
     */
    private LocalTime reservationTime;

}
