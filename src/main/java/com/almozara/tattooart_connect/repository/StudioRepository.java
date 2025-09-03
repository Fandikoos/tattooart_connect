package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
    List<StudioEntity> findByNameContainingIgnoreCase(String name);
}
