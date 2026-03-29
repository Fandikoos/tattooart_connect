package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findByTattooStudioIdStudio(Long idStudio);

    List<BookingEntity> findByArtistIdArtist(Long idArtist);
}