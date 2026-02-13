package com.almozara.tattooart_connect.service.review;

import com.almozara.tattooart_connect.domain.ReviewEntity;
import com.almozara.tattooart_connect.dto.ReviewDto;
import com.almozara.tattooart_connect.global.exceptions.ExistingIdException;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.global.exceptions.UserException;
import com.almozara.tattooart_connect.mapper.ReviewMapper;
import com.almozara.tattooart_connect.repository.ReviewRepository;
import com.almozara.tattooart_connect.service.studio.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    @Transactional
    public ReviewDto create(ReviewDto review) {
        if (review.getIdReview() != null) {
            throw new ExistingIdException("Error to create because review has an id");
        }
        if (review.getIdUser() == null) {
            throw new UserException("It is necessary user id, actually is null");
        }
        review.setCreatedAt(LocalDateTime.now());
        ReviewEntity entity = reviewMapper.transferToEntity(review);
        ReviewEntity saved = reviewRepository.save(entity);
        Long idStudio = saved.getTattooStudio().getIdStudio();

        BigDecimal averageRating = reviewRepository.calculateAverageRatingByStudio(idStudio);
        studioService.updateRating(idStudio, averageRating);
        return reviewMapper.transferToDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long idReview) {
        ReviewEntity reviewEntity = reviewRepository.findById(idReview)
                .orElseThrow(() -> new NotFoundException("Review with id " + idReview + " not found"));
        Long idStudio = reviewEntity.getTattooStudio().getIdStudio();
        reviewRepository.delete(reviewEntity);

        BigDecimal averageRating = calculateAverageRatingByIdStudio(idStudio);
        studioService.updateRating(idStudio, averageRating);
    }

    /*
     Se utiliza transactional en metodo donde llamamos a repositorios, se hacen operaciones que no sean selects,
      si son selects es buena práctica utilizar el @Transactional(readOnly = true) si son selects complejos.
      Se utiliza para que se completen las operaciones, o se hace todo o no se hace nada */
    @Override
    @Transactional
    public void update(Long idReview, ReviewDto review) {
        ReviewEntity reviewEntity = reviewRepository.findById(idReview)
                .orElseThrow(() -> new NotFoundException("Review not found"));

        reviewEntity.setRating(review.getRating());
        reviewEntity.setReview(review.getReview());
        ReviewEntity updated = reviewRepository.save(reviewEntity);
        Long idStudio = updated.getTattooStudio().getIdStudio();

        BigDecimal avg = reviewRepository.calculateAverageRatingByStudio(idStudio);
        studioService.updateRating(idStudio, avg);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getAll() {
        return reviewMapper.transferToDtoList(reviewRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getByStudio(Long idStudio) {
        List<ReviewEntity> reviewsByStudio = reviewRepository.findByTattooStudioIdStudio(idStudio);
        if (reviewsByStudio != null) {
            return reviewMapper.transferToDtoList(reviewsByStudio);
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public BigDecimal calculateAverageRatingByIdStudio(Long idStudio) {
        return reviewRepository.calculateAverageRatingByStudio(idStudio);
    }
}
