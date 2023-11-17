package com.example.reservation.store.model;

import com.example.reservation.member.entity.Member;
import com.example.reservation.store.entity.Store;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private Member member;
    private String storeName;
    private String location;
    private String phoneNumber;

    public static StoreDto fromEntity(Store store) {
        return StoreDto.builder()
                .member(store.getMember())
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }
}
