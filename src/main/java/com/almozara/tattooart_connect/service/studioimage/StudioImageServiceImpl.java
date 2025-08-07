package com.almozara.tattooart_connect.service.studioimage;

import com.almozara.tattooart_connect.domain.StudioImageEntity;
import com.almozara.tattooart_connect.dto.StudioImageDto;
import com.almozara.tattooart_connect.mapper.StudioImageMapper;
import com.almozara.tattooart_connect.repository.StudioImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioImageServiceImpl implements StudioImageService{

    @Autowired
    private StudioImageMapper studioImageMapper;
    @Autowired
    private StudioImageRepository studioImageRepository;

    @Override
    public List<StudioImageDto> findAll() {
       List<StudioImageEntity> studioImageEntities = studioImageRepository.findAll();
       return studioImageMapper.transferToDtoList(studioImageEntities);
    }

    @Override
    public StudioImageDto findById(Long idStudioImage) {
        StudioImageEntity studioImage = studioImageRepository.findById(idStudioImage)
                .orElseThrow(() -> new RuntimeException("Studio Image not found"));

        return studioImageMapper.transferToDto(studioImage);
    }
}
