package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.FavouriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Long> {
    List<FavouriteEntity> findByUserEntityIdUser(Long idUser);
}
