package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {
    List<ArtistEntity> findByTattooStudioIdStudio(Long tattooStudioId);

    List<ArtistEntity> findByTattooStudio_User_IdUser(Long idUser);
}
