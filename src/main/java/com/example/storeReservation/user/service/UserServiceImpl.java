package com.example.storeReservation.user.service;

import com.example.storeReservation.auth.type.MemberType;
import com.example.storeReservation.global.exception.CustomException;
import com.example.storeReservation.user.dto.RegisterUser;
import com.example.storeReservation.user.dto.UserDto;
import com.example.storeReservation.user.entity.User;
import com.example.storeReservation.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.storeReservation.global.type.ErrorCode.ALREADY_EXIST_USER;
import static com.example.storeReservation.global.type.ErrorCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto register(RegisterUser user) {
        boolean exists = this.userRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new CustomException(ALREADY_EXIST_USER);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        User savedUser = this.userRepository.save(User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .memberType(MemberType.USER)
                .build());

        return UserDto.fromEntity(savedUser);
    }

    @Override
    public UserDto MemberDetail(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        return UserDto.fromEntity(user);
    }

}
