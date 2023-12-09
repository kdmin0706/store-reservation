package com.example.store.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class CreateStore {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private Long managerId;

        @NotBlank
        private String storeName;

        @NotBlank
        private String location;

        @NotBlank
        private String phoneNumber;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String storeName;

        public static Response from(StoreDto storeDto) {
            return Response.builder()
                    .storeName(storeDto.getStoreName())
                    .build();
        }
    }

}
