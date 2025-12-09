package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByTattooStudioIdStudio(Long idStudio);
}
