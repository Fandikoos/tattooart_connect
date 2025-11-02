package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
    List<StudioEntity> findByNameContainingIgnoreCase(String name);
    List<StudioEntity> findByidStudioIn(List<Long> idStudios);
    // Consulta accediendo a la entidad de User (select s.* from studios where s.user_id = :idUser;)
    List<StudioEntity> findByUser_IdUser(Long idUser);

}
