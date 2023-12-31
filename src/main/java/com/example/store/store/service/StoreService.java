package com.example.store.store.service;

import com.example.store.store.dto.CreateStore;
import com.example.store.store.dto.StoreDto;
import com.example.store.store.dto.UpdateStore;

import java.util.List;

public interface StoreService {
    StoreDto createStore(CreateStore.Request request);

    void deleteStore(Long managerId, Long storeId);

    StoreDto updateStore(Long id, UpdateStore.Request request);

    StoreDto detailStore(String name);

    List<StoreDto> searchStoreList(Long id);
}
