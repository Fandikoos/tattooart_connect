package com.almozara.tattooart_connect.service.studioimage;

import com.almozara.tattooart_connect.domain.StudioImageEntity;
import com.almozara.tattooart_connect.dto.StudioImageDto;
import com.almozara.tattooart_connect.repository.StudioImageRepository;
import com.almozara.tattooart_connect.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioImageServiceImpl implements StudioImageService{

    @Autowired
    private ModelMapperUtil modelMapperUtil;
    @Autowired
    private StudioImageRepository studioImageRepository;

    @Override
    public List<StudioImageDto> findAll() {
        return studioImageRepository.findAll().stream()
                .map(studioImage -> modelMapperUtil.mapEntityToDto(studioImage, StudioImageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudioImageDto findById(Long idStudioImage) {
        StudioImageEntity studioImage = studioImageRepository.findById(idStudioImage)
                .orElseThrow(() -> new RuntimeException("Studio Image not found"));

        return modelMapperUtil.mapEntityToDto(studioImage, StudioImageDto.class);
    }
}
