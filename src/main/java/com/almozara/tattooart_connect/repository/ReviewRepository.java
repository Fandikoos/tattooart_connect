package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByTattooStudioIdStudio(Long idStudio);

    @Query("SELECT COALESCE(AVG(r.rating), 0)" +
            "    FROM ReviewEntity r" +
            "    WHERE r.tattooStudio.idStudio = :idStudio")
    BigDecimal calculateAverageRatingByStudio(@Param("idStudio") Long idStudio);
}
