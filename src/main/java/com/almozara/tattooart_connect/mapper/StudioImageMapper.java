package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.StudioImageEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.dto.StudioImageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudioImageMapper {

    StudioImageDto transferToDto(StudioImageEntity studioImageEntity);
    StudioImageEntity transferToEntity(StudioImageDto StudioImageDto);
    List<StudioImageDto> transferToDtoList(List<StudioImageEntity> StudioImageEntities);
}
