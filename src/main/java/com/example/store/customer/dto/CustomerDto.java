package com.example.store.customer.dto;

import com.example.store.auth.type.MemberType;
import com.example.store.customer.entity.Customer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String username;
    private MemberType memberType;
    private String email;
    private String password;
    private String phoneNumber;

    public static CustomerDto fromEntity(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .memberType(customer.getMemberType())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
