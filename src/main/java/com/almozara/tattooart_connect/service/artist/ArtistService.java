package com.almozara.tattooart_connect.service.artist;

import com.almozara.tattooart_connect.dto.ArtistDto;

import java.util.List;

public interface ArtistService {

    ArtistDto createArtist(ArtistDto artistDto);
    List<ArtistDto> findAllArtist();
    List<ArtistDto> findByIdStudio(Long idTattooStudio);
    ArtistDto findById(Long idArtist);
    void update(Long idArtist, ArtistDto artistDto);
    void delete(Long idArtist);

}
