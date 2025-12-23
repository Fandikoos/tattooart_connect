package com.almozara.tattooart_connect;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.mapper.ArtistMapper;
import com.almozara.tattooart_connect.repository.ArtistRepository;
import com.almozara.tattooart_connect.service.artist.ArtistServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private ArtistMapper artistMapper;
    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    public void shouldCreate() {
        ArtistEntity artistEntityCreated = ArtistEntity.builder()
                .idArtist(1L)
                .name("Juan")
                .surname("Pérez")
                .secondSurname("González")
                .email("juan@gmail.com")
                .dni("75215421E")
                .phone(687543123)
                .build();
        ArtistDto artistToCreate = ArtistDto.builder()
                .idArtist(null)
                .name("Juan")
                .surname("Pérez")
                .secondSurname("González")
                .email("juan@gmail.com")
                .dni("75215421E")
                .phone(687543123)
                .build();
        ArtistDto expectedArtistAfterCreate = ArtistDto.builder()
                .idArtist(1L)
                .name("Juan")
                .surname("Pérez")
                .secondSurname("González")
                .email("juan@gmail.com")
                .dni("75215421E")
                .phone(687543123)
                .build();

        when(artistMapper.transferToEntity(artistToCreate)).thenReturn(artistEntityCreated);
        when(artistRepository.save(artistEntityCreated)).thenReturn(artistEntityCreated);
        when(artistMapper.transferToDto(artistEntityCreated)).thenReturn(expectedArtistAfterCreate);

        ArtistDto resultArtist = artistService.createArtist(artistToCreate);
        assertNotNull(resultArtist);
        assertEquals(1L, resultArtist.getIdArtist());
        assertEquals("Juan", resultArtist.getName());
    }

    @Test
    void shouldDelete() {
        ArtistEntity artistEntityCreated = ArtistEntity.builder()
                .idArtist(1L)
                .name("Juan")
                .surname("Pérez")
                .secondSurname("González")
                .email("juan@gmail.com")
                .dni("75215421E")
                .phone(687543123)
                .build();

        when(artistRepository.findById(artistEntityCreated.getIdArtist())).thenReturn(Optional.of(artistEntityCreated));
        artistService.delete(artistEntityCreated.getIdArtist());

        verify(artistRepository).findById(artistEntityCreated.getIdArtist());
        verify(artistRepository).delete(artistEntityCreated);
        verifyNoMoreInteractions(artistRepository);
    }

    @Test
    void shouldThrowExceptionWhenArtistNotFound() {
        // given
        Long id = 1L;
        when(artistRepository.findById(id))
                .thenReturn(Optional.empty());

        // when + then
        assertThrows(NotFoundException.class, () ->
                artistService.delete(id)
        );

        verify(artistRepository).findById(id);
        verify(artistRepository, never()).delete(any());
    }
}


