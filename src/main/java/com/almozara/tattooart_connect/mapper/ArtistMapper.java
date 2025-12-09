package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.repository.StudioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ArtistMapper {

    @Autowired
    protected StudioRepository studioRepository;

    // Entity -> DTO
    @Mapping(source = "tattooStudio.idStudio", target = "idTattooStudio")
    public abstract ArtistDto transferToDto(ArtistEntity artistEntity);

    // DTO -> Entity
    @Mapping(source = "idTattooStudio", target = "tattooStudio")
    public abstract ArtistEntity transferToEntity(ArtistDto artistDto);

    public abstract List<ArtistDto> transferToDtoList(List<ArtistEntity> artistEntities);

    // Long -> StudioEntity (MapStruct lo usará automáticamente)
    protected StudioEntity map(Long id) {
        return studioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Studio not found"));
    }
}
