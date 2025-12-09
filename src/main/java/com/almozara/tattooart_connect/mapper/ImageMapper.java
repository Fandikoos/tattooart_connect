package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ImageEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.ImageDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.repository.StudioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ImageMapper {

    @Autowired
    protected StudioRepository studioRepository;

    // Entity -> DTO
    @Mapping(source = "tattooStudio.idStudio", target = "idStudio")
    public abstract ImageDto transferToDto(ImageEntity imageEntity);

    // DTO -> Entity
    @Mapping(source = "idStudio", target = "tattooStudio")
    public abstract ImageEntity transferToEntity(ImageDto imageDto);

    public abstract List<ImageDto> transferToDtoList(List<ImageEntity> imageEntities);

    protected StudioEntity map(Long id) {
        return studioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Studio not found"));
    }
}
