package com.almozara.tattooart_connect.service.artist;

import com.almozara.tattooart_connect.dto.ArtistDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArtistService {
    ArtistDto createArtist(ArtistDto artistDto);

    List<ArtistDto> findAllArtist();

    ArtistDto findById(Long idArtist);
}
