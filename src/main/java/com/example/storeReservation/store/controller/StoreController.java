package com.example.storeReservation.store.controller;

import com.example.storeReservation.store.dto.CreateStore;
import com.example.storeReservation.store.dto.UpdateStore;
import com.example.storeReservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    /**
     * 매장 등록
     * @param request: 매장 등록 시에 필요한 데이터
     * @return : 매장 등록 완료 데이터
     */
    @PostMapping("/partner/create")
    @PreAuthorize("hasRole('PARTNER')")
    public CreateStore.Response createStore(
            @RequestBody CreateStore.Request request
    ) {
        return CreateStore.Response.from(this.storeService.createStore(request));
    }

    /**
     * 매장 관련 수정
     * @param id: 매장 ID
     * @param request : 매장 이름과 위치
     * @return : 매장 수정 완료 데이터
     */
    @PutMapping("/partner/update/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public UpdateStore.Response updateStore(
            @PathVariable Long id,
            @RequestBody UpdateStore.Request request
    ) {
        return UpdateStore.Response.from(this.storeService.updateStore(id, request));
    }

    /**
     * 매장 삭제
     * @param id: 매장 아이디
     * @return 매장 삭제 완료 Message
     */
    @DeleteMapping("/partner/delete/{id}")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> deleteStore(@PathVariable Long id) {
        this.storeService.deleteStore(id);
        return ResponseEntity.ok("매장 삭제가 완료되었습니다.");
    }

    /**
     * 매장 관련 세부 정보
     *
     * @param name 매장 이름
     * @return 매장 정보
     */
    @GetMapping("/detail/{name}")
    @PreAuthorize("hasAnyRole('USER','PARTNER')")
    public ResponseEntity<?> storeDetail(@PathVariable String name) {
        return ResponseEntity.ok(this.storeService.detailStore(name));
    }

    /**
     * 매니저로 등록되어 있는 매장 리스트
     *
     * @param id : 매니저 아이디
     * @return 매장 리스트
     */
    @GetMapping("/partner/list")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> getStoreList(@RequestParam("managerid") Long id) {
        return ResponseEntity.ok(this.storeService.searchStoreList(id));
    }
}
