package com.example.store.store.repository;

import com.example.store.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByStoreName(String name);
    Optional<Store> findByStoreName(String storeName);

    /**
     * 매니저가 등록한 매장의 정보
     * @param managerId: 매니저 아이디
     * @return List<Store>
     */
    @Query(" select s from Store s " +
            "where s.manager.id = :managerId")
    List<Store> findStoreByManagerId(
            @Param("managerId") Long managerId);
}
