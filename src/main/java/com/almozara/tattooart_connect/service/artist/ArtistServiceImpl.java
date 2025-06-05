package com.almozara.tattooart_connect.service.artist;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.repository.ArtistRepository;
import com.almozara.tattooart_connect.service.storage.StorageService;
import com.almozara.tattooart_connect.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    private ModelMapperUtil modelMapperUtil;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public ArtistDto createArtist(ArtistDto artistDto) {
        // Convierte Dto -> Entidad
        ArtistEntity artistEntity = modelMapperUtil.mapDtoToEntity(artistDto, ArtistEntity.class);

        // Lo guarda en BBDD
        ArtistEntity savedArtists = artistRepository.save(artistEntity);

        // Convierte Entidad -> Dto (para la respuesta)
        return modelMapperUtil.mapEntityToDto(savedArtists, ArtistDto.class);
    }

    @Override
    public List<ArtistDto> findAllArtist() {
        // Obtienes las entidades, las transforma en dtos y las devuelve
        return artistRepository.findAll().stream()
                .map(artistEntity -> modelMapperUtil.mapEntityToDto(artistEntity, ArtistDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDto findById(Long idArtist) {
        ArtistEntity artistEntity = artistRepository.findById(idArtist)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        return modelMapperUtil.mapEntityToDto(artistEntity, ArtistDto.class);
    }
}
