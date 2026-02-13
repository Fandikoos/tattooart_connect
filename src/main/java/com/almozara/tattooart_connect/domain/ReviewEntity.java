package com.almozara.tattooart_connect.domain;


import com.almozara.tattooart_connect.security.domain.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = ReviewEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReviewEntity {

    public static final String TABLE_NAME = "ET_REVIEW";

    public static final String ID_REVIEW_COLUMN = "ID_REVIEW";
    public static final String CREATED_AT_COLUMN = "CREATED_AT";
    public static final String REVIEW_COLUMN = "REVIEW";
    public static final String RATING_COLUMN = "RATING";
    public static final String TATTOO_STUDIO_COLUMN = "ID_STUDIO";
    public static final String USER_COLUMN = "ID_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_REVIEW_COLUMN)
    @EqualsAndHashCode.Include
    private Long idReview;

    @Column(name = CREATED_AT_COLUMN, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = REVIEW_COLUMN)
    private String review;

    @Column(name = RATING_COLUMN, nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TATTOO_STUDIO_COLUMN, referencedColumnName = StudioEntity.ID_STUDIO_COLUMN, nullable = false)
    private StudioEntity tattooStudio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_COLUMN, referencedColumnName = UserEntity.ID_USER_COLUMN, nullable = false)
    private UserEntity user;

}
