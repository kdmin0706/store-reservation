package com.example.storeReservation.review.controller;

import com.example.storeReservation.review.dto.CreateReview;
import com.example.storeReservation.review.dto.UpdateReview;
import com.example.storeReservation.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     * @param userId : 유저 아이디
     * @param storeId : 매장 아이디
     * @param reservationId : 예약 아이디
     * @param request : 작성 내용
     * @return ReviewDto
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateReview.Response createReview(
            @RequestParam(name = "userid") Long userId,
            @RequestParam(name = "storeid") Long storeId,
            @RequestParam(name = "reservationid") Long reservationId,
            @RequestBody CreateReview.Request request
    ) {
        return CreateReview.Response.from(
                this.reviewService.createReview(userId, storeId, reservationId, request));
    }

    /**
     * 리뷰 삭제
     * @param id : 예약한 아이디
     * @return : 삭제 완료 Message
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER')")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        this.reviewService.deleteReview(id);
        return ResponseEntity.ok("리뷰 삭제가 완료되었습니다.");
    }

    /**
     * 리뷰 수정
     * @param reviewId : 리뷰 아이디
     * @param request : 리뷰 변경 내용
     * @return : updateReviewDto
     */
    @PutMapping("/update/{reviewId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public UpdateReview.Response updateReview(
            @PathVariable Long reviewId,
            @RequestBody UpdateReview.Request request
    ) {
        return UpdateReview.Response.from(
                this.reviewService.updateReview(reviewId, request));
    }
}
