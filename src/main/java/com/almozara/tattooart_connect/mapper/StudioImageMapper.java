package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.StudioImageEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.dto.StudioImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudioImageMapper {

    @Mapping(source = "tattooStudio.idStudio", target = "idTattooStudio")
    StudioImageDto transferToDto(StudioImageEntity studioImageEntity);
    @Mapping(target = "tattooStudio", ignore = true)
    StudioImageEntity transferToEntity(StudioImageDto StudioImageDto);
    List<StudioImageDto> transferToDtoList(List<StudioImageEntity> StudioImageEntities);
}
