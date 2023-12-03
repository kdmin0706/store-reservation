package com.example.storeReservation.review.service;

import com.example.storeReservation.customer.entity.Customer;
import com.example.storeReservation.customer.repository.CustomerRepository;
import com.example.storeReservation.global.exception.CustomException;
import com.example.storeReservation.reservation.entity.Reservation;
import com.example.storeReservation.reservation.repository.ReservationRepository;
import com.example.storeReservation.reservation.type.ReservationStatus;
import com.example.storeReservation.review.dto.CreateReview;
import com.example.storeReservation.review.dto.ReviewDto;
import com.example.storeReservation.review.dto.UpdateReview;
import com.example.storeReservation.review.entity.Review;
import com.example.storeReservation.review.repository.ReviewRepository;
import com.example.storeReservation.store.entity.Store;
import com.example.storeReservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.storeReservation.global.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewDto createReview(Long userId, Long storeId, Long reservationId,
                                  CreateReview.Request request) {
        Customer customer = this.customerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Store store = this.storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

        Reservation reservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

        validationReviewStatus(customer, reservation);

        Review savedReview = this.reviewRepository.save(Review.builder()
                        .content(request.getContent())
                        .rating(request.getRating())
                        .customer(customer)
                        .store(store)
                        .reservation(reservation)
                        .build());

        return ReviewDto.fromEntity(savedReview);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        this.reviewRepository.delete(this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND)));
    }

    @Override
    @Transactional
    public ReviewDto updateReview(Long reviewId, UpdateReview.Request request) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(REVIEW_NOT_FOUND));

        validationUpdateReview(request);

        review.setRating(request.getRating());
        review.setContent(request.getContent());
        Review savedReview = this.reviewRepository.save(review);

        return ReviewDto.fromEntity(savedReview);
    }

    /**
     * 리뷰 관련 수정 시 유효성 검사
     * 1. 별점이 5점 이상이거나 0점 미만인 경우
     * 2. 텍스트의 길이가 300자 이상인 경우
     */
    private void validationUpdateReview(UpdateReview.Request request) {
        if (request.getRating() > 5 || request.getRating() < 0) {
            throw new CustomException(REVIEW_RATING_RANGE_OVER);
        }
        if (request.getContent().length() > 300) {
            throw new CustomException(REVIEW_TEXT_TOO_LONG);
        }
    }

    /**
     * 리뷰 관련 유효성 검사
     * 1. 예약한 유저와 리뷰를 쓰려는 유저가 다른 경우
     * 2. 해당 예약에 대한 리뷰가 존재하는 경우
     * 3. 해당 리뷰를 쓸 수 있는 상태인지 확인(예약 사용 완료 확인)
     */
    private void validationReviewStatus(Customer customer, Reservation reservation) {
        if (!reservation.getCustomer().getId().equals(customer.getId())) {
            throw new CustomException(USER_AUTHORITY_NOT_MATCH);
        }
        if (this.reviewRepository.existsByReservationId(reservation.getId())) {
            throw new CustomException(ALREADY_EXIST_REVIEW);
        }
        if (!reservation.getReservationStatus().equals(ReservationStatus.USE_COMPLETED)) {
            throw new CustomException(REVIEW_NOT_AVAILABLE);
        }
    }
}
