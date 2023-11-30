package com.example.storeReservation.manager.service;

import com.example.storeReservation.auth.type.MemberType;
import com.example.storeReservation.global.exception.CustomException;
import com.example.storeReservation.manager.dto.ManagerDto;
import com.example.storeReservation.manager.dto.RegisterManager;
import com.example.storeReservation.manager.entity.Manager;
import com.example.storeReservation.manager.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.storeReservation.global.type.ErrorCode.ALREADY_EXIST_USER;
import static com.example.storeReservation.global.type.ErrorCode.MANAGER_NOT_FOUND;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final PasswordEncoder passwordEncoder;
    private final ManagerRepository managerRepository;

    @Override
    @Transactional
    public ManagerDto register(RegisterManager user) {
        boolean exists = this.managerRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new CustomException(ALREADY_EXIST_USER);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Manager savedManager = this.managerRepository.save(Manager.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .memberType(MemberType.PARTNER)
                .build());

        return ManagerDto.fromEntity(savedManager);
    }

    @Override
    public ManagerDto MemberDetail(Long userId) {
        Manager manager = this.managerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));

        return ManagerDto.fromEntity(manager);
    }

}
