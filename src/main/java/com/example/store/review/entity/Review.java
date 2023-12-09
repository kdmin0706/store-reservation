package com.example.store.review.entity;

import com.example.store.customer.entity.Customer;
import com.example.store.global.entity.BaseEntity;
import com.example.store.reservation.entity.Reservation;
import com.example.store.store.entity.Store;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {
    /**
     * 리뷰 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 리뷰 내용
     */
    @Column(length = 300)
    private String content;

    /**
     * 평점
     */
    @Column(precision = 2, scale = 1)
    @Digits(integer = 1, fraction = 1)
    private double rating;

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
     * reservation 아이디
     */
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
