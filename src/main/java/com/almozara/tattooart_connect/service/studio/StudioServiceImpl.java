package com.almozara.tattooart_connect.service.studio;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.mapper.StudioMapper;
import com.almozara.tattooart_connect.repository.StudioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioServiceImpl implements StudioService{

    @Autowired
    private StudioMapper studioMapper;
    @Autowired
    private StudioRepository studioRepository;

    @Override
    public List<StudioDto> findAll() {
        List<StudioEntity> studioEntities = studioRepository.findAll();
        return studioMapper.transferToDtoList(studioEntities);
    }

    @Override
    public StudioDto findById(Long idStudio) {
        StudioEntity studioEntity = studioRepository.findById(idStudio)
                .orElseThrow(() -> new RuntimeException("Studio not found"));

        return studioMapper.transferToDto(studioEntity);
    }


    @Override
    public void update(Long idStudio, StudioDto studioDto) {
        StudioEntity existingStudioEntity = studioRepository.findById(idStudio)
                .orElseThrow(() -> new RuntimeException("Studio not found"));

        if (existingStudioEntity != null){
            existingStudioEntity.setAddress(studioDto.getAddress());
            existingStudioEntity.setLogo(studioDto.getLogo());
            existingStudioEntity.setLatitud(studioDto.getLatitud());
            existingStudioEntity.setLongitud(studioDto.getLongitud());
            existingStudioEntity.setName(studioDto.getName());
            existingStudioEntity.setRating(studioDto.getRating());
            existingStudioEntity.setDescription(studioDto.getDescription());
            existingStudioEntity.setOpenSchedule(studioDto.getOpenSchedule());
            existingStudioEntity.setCloseSchedule(studioDto.getCloseSchedule());
            studioRepository.save(existingStudioEntity);
        }
    }

    @Override
    public StudioDto create(StudioDto studioDto) {
        StudioEntity studioEntity = studioMapper.transferToEntity(studioDto);
        studioRepository.save(studioEntity);
        return studioMapper.transferToDto(studioEntity);
    }

    @Override
    public void delete(Long idStudio) {
        StudioEntity studioEntity = studioRepository.findById(idStudio)
                .orElseThrow(() -> new RuntimeException("Studio not found"));
        if (studioEntity != null){
            studioRepository.delete(studioEntity);
        }
    }

    @Override
    public List<StudioDto> findByName(String name) {
        List<StudioEntity> studioEntitiesByName = studioRepository.findByNameContainingIgnoreCase(name);
        if (studioEntitiesByName != null && !studioEntitiesByName.isEmpty()){
            return studioMapper.transferToDtoList(studioEntitiesByName);
        }
        return new ArrayList<>();
    }
}
