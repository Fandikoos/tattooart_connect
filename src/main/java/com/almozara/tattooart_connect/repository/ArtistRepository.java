package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    ArtistEntity findByEmail(String email);
}
