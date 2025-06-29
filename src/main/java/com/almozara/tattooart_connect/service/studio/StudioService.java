package com.almozara.tattooart_connect.service.studio;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;

import java.util.List;

public interface StudioService {

    List<StudioDto> findAll();
    StudioDto findById(Long idStudio);
}
