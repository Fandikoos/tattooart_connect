package com.almozara.tattooart_connect.service.studio;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.repository.StudioRepository;
import com.almozara.tattooart_connect.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioServiceImpl implements StudioService{

    @Autowired
    private ModelMapperUtil modelMapperUtil;
    @Autowired
    private StudioRepository studioRepository;

    @Override
    public List<StudioDto> findAll() {
        return studioRepository.findAll().stream()
                .map(studioEntity -> modelMapperUtil.mapEntityToDto(studioEntity, StudioDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudioDto findById(Long idStudio) {
        StudioEntity studioEntity = studioRepository.findById(idStudio)
                .orElseThrow(() -> new RuntimeException("Studio not found"));

        return modelMapperUtil.mapEntityToDto(studioEntity, StudioDto.class);
    }
}
