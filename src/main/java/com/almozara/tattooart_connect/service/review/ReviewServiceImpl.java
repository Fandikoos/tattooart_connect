package com.almozara.tattooart_connect.service.review;

import com.almozara.tattooart_connect.domain.ReviewEntity;
import com.almozara.tattooart_connect.dto.ReviewDto;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.global.exceptions.ExistingIdException;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.global.exceptions.UserException;
import com.almozara.tattooart_connect.mapper.ReviewMapper;
import com.almozara.tattooart_connect.repository.ReviewRepository;
import com.almozara.tattooart_connect.service.studio.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final StudioService studioService;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewDto create(ReviewDto review) {
        if (review.getIdReview() != null) {
            throw new ExistingIdException("Error to create because review has an id");
        }
        if (review.getIdUser() == null) {
            throw new UserException("It is necessary user id, actually is null");
        }
        review.setCreatedAt(LocalDateTime.now());
        calculateAverateRatingStudio(review);
        ReviewEntity reviewEntity = reviewMapper.transferToEntity(review);
        return reviewMapper.transferToDto(reviewRepository.save(reviewEntity));
    }

    private void calculateAverateRatingStudio(ReviewDto review) {
        StudioDto studioDto = studioService.findById(review.getIdTattooStudio());
        if (studioDto != null) {
            BigDecimal actualStudioRating = studioDto.getRating();
            BigDecimal addRatingValue = actualStudioRating.add(review.getRating());
            BigDecimal result;
            if (!studioDto.getReviews().isEmpty()) {
                result = addRatingValue.divide(BigDecimal.valueOf(studioDto.getReviews().size()), 2, RoundingMode.HALF_UP);
            } else {
                result = addRatingValue;
            }
            studioDto.setRating(result);
            studioService.update(studioDto.getIdStudio(), studioDto);
        }
    }

    @Override
    public void delete(Long idReview) {
        ReviewEntity reviewEntity = reviewRepository.findById(idReview)
                .orElseThrow(() -> new NotFoundException("Review with id " + idReview + " not found"));
        reviewRepository.delete(reviewEntity);
    }

    @Override
    public void update(Long idReview, ReviewDto review) {
        ReviewEntity existingReview = reviewRepository.findById(idReview)
                .orElseThrow(() -> new NotFoundException("Review with id " + idReview + " not found"));

        existingReview.setRating(review.getRating());
        existingReview.setReview(review.getReview());
        reviewRepository.save(existingReview);
    }

    @Override
    public List<ReviewDto> getAll() {
        return reviewMapper.transferToDtoList(reviewRepository.findAll());
    }

    @Override
    public List<ReviewDto> getByStudio(Long idStudio) {
        List<ReviewEntity> reviewsByStudio = reviewRepository.findByTattooStudioIdStudio(idStudio);
        if (reviewsByStudio != null) {
            return reviewMapper.transferToDtoList(reviewsByStudio);
        }
        return Collections.emptyList();
    }
}
