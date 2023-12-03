package com.example.storeReservation.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UpdateReview {

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

        public static Response from(ReviewDto reviewDto) {
            return Response.builder()
                    .reviewId(reviewDto.getReviewId())
                    .content(reviewDto.getContent())
                    .rating(reviewDto.getRating())
                    .build();
        }
    }
}
