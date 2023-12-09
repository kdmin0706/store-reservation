package com.example.store.manager.service;

import com.example.store.auth.type.MemberType;
import com.example.store.global.exception.CustomException;
import com.example.store.manager.dto.ManagerDto;
import com.example.store.manager.dto.RegisterManager;
import com.example.store.manager.entity.Manager;
import com.example.store.manager.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.store.global.type.ErrorCode.ALREADY_EXIST_USER;
import static com.example.store.global.type.ErrorCode.MANAGER_NOT_FOUND;

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
    public ManagerDto memberDetail(Long userId) {
        Manager manager = this.managerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));

        return ManagerDto.fromEntity(manager);
    }

}
