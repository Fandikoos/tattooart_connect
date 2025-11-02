package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ArtistMapper.class,
        StudioImageMapper.class
})
public interface StudioMapper {

    // Mapeo de entidad a dto
    @Mapping(source = "user.idUser", target = "idUser")
    StudioDto transferToDto(StudioEntity studioEntity);

    // Mapero de dto a entidad
    @Mapping(target = "user", ignore = true)
    StudioEntity transferToEntity(StudioDto studioDto);

    // Lista de entidades a lista de Dtos
    List<StudioDto> transferToDtoList(List<StudioEntity> studioEntities);
}
