package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByTattooStudio_IdStudio(Long idStudio);
}
