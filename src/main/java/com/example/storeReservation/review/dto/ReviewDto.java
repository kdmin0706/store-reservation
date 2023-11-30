package com.example.storeReservation.review.dto;

import com.example.storeReservation.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewDto {
    private Long reviewId;
    private String content;
    private double rating;
    private String username;
    private String storeName;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .username(review.getCustomer().getUsername())
                .storeName(review.getStore().getStoreName())
                .build();
    }
}
