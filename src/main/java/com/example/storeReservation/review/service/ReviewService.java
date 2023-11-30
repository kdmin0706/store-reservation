package com.example.storeReservation.review.service;

import com.example.storeReservation.review.dto.CreateReview;
import com.example.storeReservation.review.dto.ReviewDto;
import com.example.storeReservation.review.dto.UpdateReview;

public interface ReviewService {
    /**
     * 리뷰 등록
     * @param userId: 유저 아이디
     * @param storeId: 매장 아이디
     * @param reservationId: 예약 아이디
     * @param request: 리뷰 작성 데이터
     * @return ReviewDto
     */
    ReviewDto createReview(Long userId, Long storeId, Long reservationId,
                            CreateReview.Request request);

    /**
     * 리뷰 삭제
     * @param reviewId : 리뷰를 작성한 아이디
     */
    void deleteReview(Long reviewId);

    /**
     * 리뷰 수정
     * @param reviewId : 리뷰 아이디
     * @param request : 변경할 정보
     * @return : 변경 완료 데이터
     */
    ReviewDto updateReview(Long reviewId, UpdateReview.Request request);
}
