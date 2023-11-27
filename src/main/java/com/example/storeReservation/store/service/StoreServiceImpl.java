package com.example.storeReservation.store.service;

import com.example.storeReservation.global.exception.CustomException;
import com.example.storeReservation.manager.entity.Manager;
import com.example.storeReservation.manager.repository.ManagerRepository;
import com.example.storeReservation.store.dto.CreateStore;
import com.example.storeReservation.store.dto.StoreDto;
import com.example.storeReservation.store.dto.UpdateStore;
import com.example.storeReservation.store.entity.Store;
import com.example.storeReservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.storeReservation.global.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final ManagerRepository managerRepository;

    /**
     * 매장 정보 등록
     * @param request: 매장 정보 및 매니저 아이디를 갖는 객체
     * @return 매장의 기본 정보를 갖는 객체
     */
    @Override
    @Transactional
    public StoreDto createStore(CreateStore.Request request) {
        log.info("매장 생성 시작");

        Manager manager = this.managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (this.storeRepository.existsByStoreName(request.getStoreName())) {
            throw new CustomException(ALREADY_EXIST_STORE);
        }

        log.info("매장 생성 완료");

        return StoreDto.fromEntity(this.storeRepository.save(Store.builder()
                .manager(manager)
                .storeName(request.getStoreName())
                .location(request.getLocation())
                .phoneNumber(request.getPhoneNumber())
                .build()));
    }

    /**
     * 등록되어있는 매장 삭제
     * @param id: 삭제하려는 매장 아이디
     */
    @Override
    @Transactional
    public void deleteStore(Long id) {
        log.info("매장 정보 삭제");
        this.storeRepository.delete(this.storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND)));
    }

    /**
     * 매장 위치 또는 이름 변경
     *
     * @param id : 정보를 변경하고 싶은 매장의 아이디
     * @param request : 변경하려는 매장 위치와 매장 이름을 갖는 변수
     * @return 매장 위치나 매장 이름이 변경
     */
    @Override
    @Transactional
    public StoreDto updateStore(Long id, UpdateStore.Request request) {
        log.info("매장 정보 변경");

        Store store = this.storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        store.setStoreName(request.getStoreName());
        store.setLocation(request.getLocation());

        log.info("매장 정보 변경 완료");
        return StoreDto.fromEntity(this.storeRepository.save(store));
    }

    /**
     * 매장 세부 정보 확인
     * @param name 매장 이름
     * @return 매장의 세부 정보 확인
     */
    @Override
    public StoreDto detailStore(String name) {
        log.info("매장 세부 정보 확인");
        Store store = checkStoreName(name);
        return StoreDto.fromEntity(store);
    }

    @Override
    public List<StoreDto> searchStoreList(Long id) {
        log.info("매장 리스트 확인");

        List<Store> storeList =
                this.storeRepository.findStoreByManagerId(id);

        if (storeList.isEmpty()) {
            throw new CustomException(STORE_NOT_FOUND);
        }

        log.info("매장 리스트 확인 완료");
        return storeList.stream()
                .map(StoreDto::fromEntity).collect(Collectors.toList());
    }

    private Store checkStoreName(String name) {
        return this.storeRepository.findByStoreName(name)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    }

}
