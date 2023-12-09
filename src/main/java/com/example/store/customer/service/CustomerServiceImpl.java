package com.example.store.customer.service;

import com.example.store.auth.type.MemberType;
import com.example.store.customer.dto.CustomerDto;
import com.example.store.customer.dto.RegisterCustomer;
import com.example.store.customer.entity.Customer;
import com.example.store.customer.repository.CustomerRepository;
import com.example.store.global.exception.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.store.global.type.ErrorCode.ALREADY_EXIST_USER;
import static com.example.store.global.type.ErrorCode.USER_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerDto register(RegisterCustomer user) {
        boolean exists = this.customerRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new CustomException(ALREADY_EXIST_USER);
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Customer savedCustomer = this.customerRepository.save(Customer.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .memberType(MemberType.CUSTOMER)
                .build());

        return CustomerDto.fromEntity(savedCustomer);
    }

    @Override
    public CustomerDto memberDetail(Long userId) {
        Customer customer = this.customerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));


        return CustomerDto.fromEntity(customer);
    }

}
