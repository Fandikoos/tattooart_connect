package com.almozara.tattooart_connect.service.studio;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.global.exceptions.ExistingIdException;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.global.exceptions.UserException;
import com.almozara.tattooart_connect.mapper.StudioMapper;
import com.almozara.tattooart_connect.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudioServiceImpl implements StudioService {

    private final StudioMapper studioMapper;
    private final StudioRepository studioRepository;

    @Override
    public List<StudioDto> findAll() {
        List<StudioEntity> studioEntities = studioRepository.findAll();
        return studioMapper.transferToDtoList(studioEntities);
    }

    @Override
    public StudioDto findById(Long idStudio) throws NotFoundException {
        StudioEntity studioEntity = studioRepository.findById(idStudio)
                .orElseThrow(() -> new NotFoundException("Studio with id " + idStudio + " not found"));
        return studioMapper.transferToDto(studioEntity);
    }


    @Override
    @Transactional
    public void update(Long idStudio, StudioDto studioDto) {
        StudioEntity existingStudioEntity = studioRepository.findById(idStudio)
                .orElseThrow(() -> new NotFoundException("Studio with id " + idStudio + " not found"));

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

    @Override
    @Transactional
    public StudioDto create(StudioDto studioDto) {
        if (studioDto.getIdUser() == null) {
            throw new UserException("It is necessary user id, actually is null");
        }
        if (studioDto.getIdStudio() != null) {
            throw new ExistingIdException("Error to create because studio has an id");
        }
        studioDto.setRating(new BigDecimal(0));
        StudioEntity studioEntity = studioMapper.transferToEntity(studioDto);
        studioRepository.save(studioEntity);
        return studioMapper.transferToDto(studioEntity);
    }

    @Override
    @Transactional
    public void delete(Long idStudio) {
        StudioEntity studioEntity = studioRepository.findById(idStudio)
                .orElseThrow(() -> new NotFoundException("Studio with id " + idStudio + " not found"));
        studioRepository.delete(studioEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudioDto> findByName(String name) {
        List<StudioEntity> studioEntitiesByName = studioRepository.findByNameContainingIgnoreCase(name);
        if (studioEntitiesByName != null && !studioEntitiesByName.isEmpty()) {
            return studioMapper.transferToDtoList(studioEntitiesByName);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudioDto> findByIdsStudios(List<Long> idsStudios) {
        List<StudioEntity> studioEntitiesByIds = studioRepository.findByidStudioIn(idsStudios);
        if (studioEntitiesByIds != null && !studioEntitiesByIds.isEmpty()) {
            return studioMapper.transferToDtoList(studioEntitiesByIds);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudioDto> findByUser(Long idUser) {
        List<StudioEntity> studioEntitiesByUser = studioRepository.findByUser_IdUser(idUser);
        if (studioEntitiesByUser != null && !studioEntitiesByUser.isEmpty()) {
            return studioMapper.transferToDtoList(studioEntitiesByUser);
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public void updateRating(Long idStudio, BigDecimal rating) {
        studioRepository.updateRating(idStudio, rating);
    }
}
