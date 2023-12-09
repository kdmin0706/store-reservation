package com.example.store.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CreateReview {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String content;
        private double rating;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long reviewId;
        private String content;
        private double rating;
        private String username;
        private String storeName;

        public static Response from(ReviewDto reviewDto) {
            return Response.builder()
                    .reviewId(reviewDto.getReviewId())
                    .content(reviewDto.getContent())
                    .rating(reviewDto.getRating())
                    .username(reviewDto.getUsername())
                    .storeName(reviewDto.getStoreName())
                    .build();
        }
    }
}
