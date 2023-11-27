package com.example.storeReservation.customer.service;

import com.example.storeReservation.auth.type.MemberType;
import com.example.storeReservation.global.exception.CustomException;
import com.example.storeReservation.customer.dto.RegisterCustomer;
import com.example.storeReservation.customer.dto.CustomerDto;
import com.example.storeReservation.customer.entity.Customer;
import com.example.storeReservation.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.storeReservation.global.type.ErrorCode.ALREADY_EXIST_USER;
import static com.example.storeReservation.global.type.ErrorCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository userRepository;

    @Override
    @Transactional
    public CustomerDto register(RegisterCustomer user) {
        boolean exists = this.userRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new CustomException(ALREADY_EXIST_USER);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Customer savedCustomer = this.userRepository.save(Customer.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .memberType(MemberType.CUSTOMER)
                .build());

        return CustomerDto.fromEntity(savedCustomer);
    }

    @Override
    public CustomerDto MemberDetail(Long userId) {
        Customer customer = this.userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));


        return CustomerDto.fromEntity(customer);
    }

}
