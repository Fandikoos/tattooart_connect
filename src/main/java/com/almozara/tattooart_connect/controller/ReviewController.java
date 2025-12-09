package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.ReviewDto;
import com.almozara.tattooart_connect.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + ReviewController.URL)
@RequiredArgsConstructor
public class ReviewController {

    public static final String URL = "/review";
    private final ReviewService reviewService;

    @GetMapping("/{idStudio}")
    public ResponseEntity<List<ReviewDto>> findByStudio(@PathVariable Long idStudio) {
        return new ResponseEntity<>(reviewService.getByStudio(idStudio), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> create(@RequestBody @Valid ReviewDto review) {
        return new ResponseEntity<>(reviewService.create(review), HttpStatus.CREATED);
    }

    @PutMapping("/{idReview}")
    public ResponseEntity<Void> update(@PathVariable Long idReview, @RequestBody @Valid ReviewDto review) {
        reviewService.update(idReview, review);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idReview}")
    public ResponseEntity<Void> delete(@PathVariable Long idReview) {
        reviewService.delete(idReview);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
