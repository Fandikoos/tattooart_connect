package com.almozara.tattooart_connect.service.studioimage;

import com.almozara.tattooart_connect.dto.StudioImageDto;

import java.util.List;

public interface StudioImageService {
    List<StudioImageDto> findAll();
    StudioImageDto create(StudioImageDto studioImageDto);
    void delete(Long idStudioImage);
    void update(Long idStudioImage, StudioImageDto studioImageDto);
    StudioImageDto findById(Long idStudioImage);
}
