package com.almozara.tattooart_connect.service.studio;

import com.almozara.tattooart_connect.dto.StudioDto;

import java.util.List;

public interface StudioService {

    List<StudioDto> findAll();
    StudioDto findById(Long idStudio);
    void update(Long idStudio, StudioDto studioDto);
    StudioDto create (StudioDto studioDto);
    void delete (Long idStudio);
}
