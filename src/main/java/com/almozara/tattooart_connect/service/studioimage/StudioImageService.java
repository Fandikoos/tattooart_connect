package com.almozara.tattooart_connect.service.studioimage;

import com.almozara.tattooart_connect.dto.StudioImageDto;

import java.util.List;

public interface StudioImageService {
    List<StudioImageDto> findAll();

    StudioImageDto findById(Long idStudioImage);
}
