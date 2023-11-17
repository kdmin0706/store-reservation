package com.example.reservation.store.entity;

import com.example.reservation.global.entity.BaseEntity;
import com.example.reservation.member.entity.Member;
import lombok.*;

import javax.persistence.*;

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
    private Long id;

    /**
     * Member 아이디
     */
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

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
}
