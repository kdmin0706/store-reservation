package com.example.storeReservation.manager.repository;

import com.example.storeReservation.manager.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    boolean existsByEmail(String email);
    Optional<Manager> findByEmail(String email);
}
