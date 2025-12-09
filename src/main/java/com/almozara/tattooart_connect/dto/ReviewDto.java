package com.almozara.tattooart_connect.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long idReview;
    private LocalDateTime createdAt;
    private String review;
    @Min(value = 0, message = "Rating has to be more than 0")
    @Max(value = 5, message = "Rating has to be less than 5")
    private BigDecimal rating;
    private Long idTattooStudio;
    private Long idUser;
    private String username;

}
