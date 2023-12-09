package com.example.store.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UpdateStore {
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
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String storeName;
        private String location;

        public static Response from(StoreDto storeDto) {
            return Response.builder()
                    .storeName(storeDto.getStoreName())
                    .location(storeDto.getLocation())
                    .build();
        }
    }

}
