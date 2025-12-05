package com.almozara.tattooart_connect.service.artist;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.mapper.ArtistMapper;
import com.almozara.tattooart_connect.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistMapper artistMapper;
    private final ArtistRepository artistRepository;

    @Override
    public ArtistDto createArtist(ArtistDto artistDto) {
        // Convierte Dto -> Entidad
        ArtistEntity artistEntity = artistMapper.transferToEntity(artistDto);

        // Lo guarda en BBDD
        ArtistEntity savedArtists = artistRepository.save(artistEntity);

        // Convierte Entidad -> Dto (para la respuesta)
        return artistMapper.transferToDto(savedArtists);
    }

    @Override
    public List<ArtistDto> findAllArtist() {
        // Obtienes las entidades, las transforma en dtos y las devuelve
        List<ArtistEntity> artistEntities = artistRepository.findAll();
        return artistMapper.transferToDtoList(artistEntities);
    }

    @Override
    public List<ArtistDto> findByIdStudio(Long idTattooStudio) {
        List<ArtistEntity> artistEntity = artistRepository.findByTattooStudioIdStudio(idTattooStudio);
        return artistMapper.transferToDtoList(artistEntity);
    }

    @Override
    public ArtistDto findById(Long idArtist) {
        ArtistEntity artistEntity = artistRepository.findById(idArtist)
                .orElseThrow(() -> new NotFoundException("Artist with " + idArtist + " not exist"));
        return artistMapper.transferToDto(artistEntity);
    }

    @Override
    public void update(Long idArtist, ArtistDto artistDto) {
        ArtistEntity existingArtistEntity = artistRepository.findById(idArtist)
                .orElseThrow(() -> new NotFoundException("Artist with " + idArtist + " not exist"));

        existingArtistEntity.setImageArtist(artistDto.getImageArtist());
        existingArtistEntity.setDni(artistDto.getDni());
        existingArtistEntity.setName(artistDto.getName());
        existingArtistEntity.setPhone(String.valueOf(artistDto.getPhone()));
        existingArtistEntity.setEmail(artistDto.getEmail());
        existingArtistEntity.setSurname(artistDto.getSurname());
        existingArtistEntity.setSecondSurname(artistDto.getSecondSurname());
        existingArtistEntity.getTattooStudio().setIdStudio(artistDto.getIdTattooStudio());
        artistRepository.save(existingArtistEntity);
    }

    @Override
    public void delete(Long idArtist) {
        ArtistEntity artistEntity = artistRepository.findById(idArtist)
                .orElseThrow(() -> new NotFoundException("Artist with " + idArtist + " not exist"));
        artistRepository.delete(artistEntity);
    }
}
