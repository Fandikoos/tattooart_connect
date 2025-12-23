package com.almozara.tattooart_connect.service.studio;

import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface StudioService {

    List<StudioDto> findAll();

    StudioDto findById(Long idStudio) throws NotFoundException;

    void update(Long idStudio, StudioDto studioDto);

    StudioDto create(StudioDto studioDto);

    void delete(Long idStudio);

    List<StudioDto> findByName(String name);

    List<StudioDto> findByIdsStudios(List<Long> idsStudios);

    List<StudioDto> findByUser(Long idUser);

    void updateRating(Long idStudio, BigDecimal rating);
}
