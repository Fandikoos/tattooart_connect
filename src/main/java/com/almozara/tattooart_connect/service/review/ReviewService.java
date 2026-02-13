package com.almozara.tattooart_connect.service.review;

import com.almozara.tattooart_connect.dto.ReviewDto;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewService {
    ReviewDto create(ReviewDto review);

    void delete(Long idReview);

    void update(Long idReview, ReviewDto review);

    List<ReviewDto> getAll();

    List<ReviewDto> getByStudio(Long idStudio);

    BigDecimal calculateAverageRatingByIdStudio(Long idStudio);
}
