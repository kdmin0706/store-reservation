package com.example.storeReservation.store.service;

import com.example.storeReservation.store.dto.CreateStore;
import com.example.storeReservation.store.dto.StoreDto;
import com.example.storeReservation.store.dto.UpdateStore;

import java.util.List;

public interface StoreService {
    StoreDto createStore(CreateStore.Request request);

    void deleteStore(Long id);

    StoreDto updateStore(Long id, UpdateStore.Request request);

    StoreDto detailStore(String name);

    List<StoreDto> searchStoreList(Long id);
}
