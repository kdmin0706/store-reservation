package com.example.storeReservation.store.entity;

import com.example.storeReservation.global.entity.BaseEntity;
import com.example.storeReservation.manager.entity.Manager;
import com.example.storeReservation.reservation.entity.Reservation;
import com.example.storeReservation.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {
    /**
     * 매장 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    /**
     * Manager 아이디
     */
    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    /**
     * 매장 이름
     */
    private String storeName;

    /**
     * 매장 위치
     */
    private String location;

    /**
     * 매장 전화번호
     */
    private String phoneNumber;

    /**
     * reservation 조인
     */
    @Builder.Default
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reservation> reservationList = new ArrayList<>();

    /**
     * review 조인
     */
    @Builder.Default
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();
}

