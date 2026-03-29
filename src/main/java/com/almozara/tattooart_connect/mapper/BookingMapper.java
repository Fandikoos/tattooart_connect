package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.BookingEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.BookingDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.repository.ArtistRepository;
import com.almozara.tattooart_connect.repository.StudioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookingMapper {

    @Autowired
    protected StudioRepository studioRepository;

    @Autowired
    protected ArtistRepository artistRepository;

    // Entity -> DTO
    @Mapping(source = "tattooStudio.idStudio", target = "idTattooStudio")
    @Mapping(source = "artist.idArtist", target = "idArtist")
    public abstract BookingDto transferToDto(BookingEntity entity);

    // DTO -> Entity
    @Mapping(source = "idTattooStudio", target = "tattooStudio", qualifiedByName = "bookingMapStudioFromId")
    @Mapping(source = "idArtist", target = "artist", qualifiedByName = "bookingMapArtistFromId")
    public abstract BookingEntity transferToEntity(BookingDto dto);

    public abstract List<BookingDto> transferToDtoList(List<BookingEntity> entities);

    @Named("bookingMapStudioFromId")
    protected StudioEntity mapStudio(Long id) {
        return studioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Studio not found"));
    }

    @Named("bookingMapArtistFromId")
    protected ArtistEntity mapArtist(Long id) {
        if (id == null) return null;
        return artistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Artist not found"));
    }
}