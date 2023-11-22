package com.example.storeReservation.auth.service;

import com.example.storeReservation.auth.dto.LoginInput;
import com.example.storeReservation.global.exception.CustomException;
import com.example.storeReservation.manager.entity.Manager;
import com.example.storeReservation.manager.repository.ManagerRepository;
import com.example.storeReservation.user.entity.User;
import com.example.storeReservation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.storeReservation.global.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 매니저 정보 확인 및 패스워드 매칭
     */
    public Manager authenticateManager(LoginInput input) {
        Manager manager = checkManagerEmail(input.getEmail());

        if (!this.passwordEncoder.matches(input.getPassword(), manager.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        return manager;
    }

    /**
     * 유저 정보 확인 및 패스워드 매칭
     */
    public User authenticateUser(LoginInput input) {
        User user = checkUserEmail(input.getEmail());

        if (!this.passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        return user;
    }

    /**
     * 회원 아이디를 이용하여 리포지토리에 아이디와 일치하는 회원을 찾는 메서드
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        if (this.managerRepository.existsByEmail(email)) {
            return checkManagerEmail(email);
        } else if (this.userRepository.existsByEmail(email)) {
            return checkUserEmail(email);
        }
        return null;
    }

    /**
     * 매니저 등록 이메일 확인
     *
     * @param email 이메일
     * @return 매니저 이메일이 없는 경우 에러
     */
    private Manager checkManagerEmail(String email) {
        return this.managerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));
    }

    /**
     * 유저 등록 이메일 확인
     *
     * @param email 이메일
     * @return 유저 이메일이 없는 경우 에러
     */
    private User checkUserEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }
}
