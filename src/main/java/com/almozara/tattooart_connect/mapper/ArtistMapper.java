package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// Usamos el StudioMapper para mapear directamente el Studio de Artist
@Mapper(componentModel = "spring")
public interface ArtistMapper {

    //    Esto indicaría que el campo artistEntity.tattooStudio.idStudio se va a mapear al campo idTattooStudio en el ArtistDto
    @Mapping(source = "tattooStudio.idStudio", target = "idTattooStudio")
    ArtistDto transferToDto(ArtistEntity artistEntity);
    @Mapping(target = "tattooStudio", ignore = true)
    ArtistEntity transferToEntity(ArtistDto artistDto);
    List<ArtistDto> transferToDtoList(List<ArtistEntity> artistEntities);
}
