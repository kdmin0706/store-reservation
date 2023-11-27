package com.example.storeReservation.store.dto;

import com.example.storeReservation.manager.entity.Manager;
import com.example.storeReservation.store.entity.Store;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private Manager manager;
    private String storeName;
    private String location;
    private String phoneNumber;

    public static StoreDto fromEntity(Store store) {
        return StoreDto.builder()
                .manager(store.getManager())
                .storeName(store.getStoreName())
                .location(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }
}