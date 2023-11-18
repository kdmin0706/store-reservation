package com.example.storeReservation.reservation.entity;

import com.example.storeReservation.global.entity.BaseEntity;
import com.example.storeReservation.member.entity.Member;
import com.example.storeReservation.reservation.model.type.ReservationStatus;
import com.example.storeReservation.store.entity.Store;
import lombok.*;

import javax.persistence.*;

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
     * Member 아이디
     */
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

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

}
