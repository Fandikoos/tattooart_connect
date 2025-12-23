package com.almozara.tattooart_connect.repository;

import com.almozara.tattooart_connect.domain.StudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StudioRepository extends JpaRepository<StudioEntity, Long> {
    List<StudioEntity> findByNameContainingIgnoreCase(String name);

    List<StudioEntity> findByidStudioIn(List<Long> idStudios);

    // Consulta accediendo a la entidad de User (select s.* from studios where s.user_id = :idUser;)
    List<StudioEntity> findByUser_IdUser(Long idUser);

    @Modifying
    @Query("UPDATE StudioEntity s SET s.rating = :rating WHERE s.idStudio = :idStudio")
    void updateRating(@Param("idStudio") Long idStudio, @Param("rating") BigDecimal rating);

}
