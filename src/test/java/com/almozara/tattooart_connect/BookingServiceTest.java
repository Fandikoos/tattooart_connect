package com.almozara.tattooart_connect;

import com.almozara.tattooart_connect.domain.BookingEntity;
import com.almozara.tattooart_connect.dto.BookingDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.mapper.BookingMapper;
import com.almozara.tattooart_connect.repository.BookingRepository;
import com.almozara.tattooart_connect.service.booking.BookingServiceImpl;
import com.almozara.tattooart_connect.util.enums.BookingStatusEnum;
import com.almozara.tattooart_connect.util.enums.BookingTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private BookingMapper bookingMapper;
    @InjectMocks
    private BookingServiceImpl bookingService;

    // -------------------------------------------------------------------------
    // Datos de prueba reutilizables
    // -------------------------------------------------------------------------

    private BookingEntity buildEntity(Long id) {
        return BookingEntity.builder()
                .idBooking(id)
                .title("Tatuaje floral - Marta")
                .description("Antebrazo, diseño personalizado")
                .startDateTime(LocalDateTime.of(2026, 4, 10, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 4, 10, 12, 0))
                .type(BookingTypeEnum.BOOKING)
                .status(BookingStatusEnum.PENDING)
                .clientName("Marta García")
                .clientPhone("612345678")
                .clientEmail("marta@email.com")
                .color("#FF5733")
                .build();
    }

    private BookingDto buildDto(Long id) {
        return BookingDto.builder()
                .idBooking(id)
                .title("Tatuaje floral - Marta")
                .description("Antebrazo, diseño personalizado")
                .startDateTime(LocalDateTime.of(2026, 4, 10, 10, 0))
                .endDateTime(LocalDateTime.of(2026, 4, 10, 12, 0))
                .type(BookingTypeEnum.BOOKING)
                .status(BookingStatusEnum.PENDING)
                .clientName("Marta García")
                .clientPhone("612345678")
                .clientEmail("marta@email.com")
                .color("#FF5733")
                .idTattooStudio(1L)
                .build();
    }

    // -------------------------------------------------------------------------
    // create
    // -------------------------------------------------------------------------

    @Test
    void shouldCreate() {
        BookingDto inputDto = buildDto(null);
        BookingEntity entity = buildEntity(null);
        BookingEntity savedEntity = buildEntity(1L);
        BookingDto expectedDto = buildDto(1L);

        when(bookingMapper.transferToEntity(inputDto)).thenReturn(entity);
        when(bookingRepository.save(entity)).thenReturn(savedEntity);
        when(bookingMapper.transferToDto(savedEntity)).thenReturn(expectedDto);

        BookingDto result = bookingService.create(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getIdBooking());
        assertEquals("Tatuaje floral - Marta", result.getTitle());
        assertEquals(BookingStatusEnum.PENDING, result.getStatus());
        verify(bookingRepository).save(entity);
    }

    // -------------------------------------------------------------------------
    // findAll
    // -------------------------------------------------------------------------

    @Test
    void shouldFindAll() {
        List<BookingEntity> entities = List.of(buildEntity(1L), buildEntity(2L));
        List<BookingDto> dtos = List.of(buildDto(1L), buildDto(2L));

        when(bookingRepository.findAll()).thenReturn(entities);
        when(bookingMapper.transferToDtoList(entities)).thenReturn(dtos);

        List<BookingDto> result = bookingService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookingRepository).findAll();
    }

    // -------------------------------------------------------------------------
    // findByIdStudio
    // -------------------------------------------------------------------------

    @Test
    void shouldFindByIdStudio() {
        Long idStudio = 1L;
        List<BookingEntity> entities = List.of(buildEntity(1L));
        List<BookingDto> dtos = List.of(buildDto(1L));

        when(bookingRepository.findByTattooStudioIdStudio(idStudio)).thenReturn(entities);
        when(bookingMapper.transferToDtoList(entities)).thenReturn(dtos);

        List<BookingDto> result = bookingService.findByIdStudio(idStudio);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository).findByTattooStudioIdStudio(idStudio);
    }

    // -------------------------------------------------------------------------
    // findByIdArtist
    // -------------------------------------------------------------------------

    @Test
    void shouldFindByIdArtist() {
        Long idArtist = 2L;
        List<BookingEntity> entities = List.of(buildEntity(1L));
        List<BookingDto> dtos = List.of(buildDto(1L));

        when(bookingRepository.findByArtistIdArtist(idArtist)).thenReturn(entities);
        when(bookingMapper.transferToDtoList(entities)).thenReturn(dtos);

        List<BookingDto> result = bookingService.findByIdArtist(idArtist);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(bookingRepository).findByArtistIdArtist(idArtist);
    }

    // -------------------------------------------------------------------------
    // findById
    // -------------------------------------------------------------------------

    @Test
    void shouldFindById() {
        Long id = 1L;
        BookingEntity entity = buildEntity(id);
        BookingDto expectedDto = buildDto(id);

        when(bookingRepository.findById(id)).thenReturn(Optional.of(entity));
        when(bookingMapper.transferToDto(entity)).thenReturn(expectedDto);

        BookingDto result = bookingService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getIdBooking());
        verify(bookingRepository).findById(id);
    }

    @Test
    void shouldThrowNotFoundWhenFindByIdNotExist() {
        Long id = 99L;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookingService.findById(id));

        verify(bookingRepository).findById(id);
        verify(bookingMapper, never()).transferToDto(any());
    }

    // -------------------------------------------------------------------------
    // update
    // -------------------------------------------------------------------------

    @Test
    void shouldUpdate() {
        Long id = 1L;
        BookingEntity existingEntity = buildEntity(id);
        BookingDto updateDto = buildDto(id);
        updateDto.setTitle("Nuevo título");
        updateDto.setStatus(BookingStatusEnum.CONFIRMED);

        BookingEntity savedEntity = buildEntity(id);
        savedEntity.setTitle("Nuevo título");
        savedEntity.setStatus(BookingStatusEnum.CONFIRMED);

        BookingDto expectedDto = buildDto(id);
        expectedDto.setTitle("Nuevo título");
        expectedDto.setStatus(BookingStatusEnum.CONFIRMED);

        when(bookingRepository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(bookingRepository.save(existingEntity)).thenReturn(savedEntity);
        when(bookingMapper.transferToDto(savedEntity)).thenReturn(expectedDto);

        BookingDto result = bookingService.update(id, updateDto);

        assertNotNull(result);
        assertEquals("Nuevo título", result.getTitle());
        assertEquals(BookingStatusEnum.CONFIRMED, result.getStatus());
        verify(bookingRepository).findById(id);
        verify(bookingRepository).save(existingEntity);
    }

    @Test
    void shouldThrowNotFoundWhenUpdateNotExist() {
        Long id = 99L;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookingService.update(id, buildDto(id)));

        verify(bookingRepository).findById(id);
        verify(bookingRepository, never()).save(any());
    }

    // -------------------------------------------------------------------------
    // delete
    // -------------------------------------------------------------------------

    @Test
    void shouldDelete() {
        Long id = 1L;
        BookingEntity entity = buildEntity(id);

        when(bookingRepository.findById(id)).thenReturn(Optional.of(entity));

        bookingService.delete(id);

        verify(bookingRepository).findById(id);
        verify(bookingRepository).delete(entity);
        verifyNoMoreInteractions(bookingRepository);
    }

    @Test
    void shouldThrowNotFoundWhenDeleteNotExist() {
        Long id = 99L;
        when(bookingRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> bookingService.delete(id));

        verify(bookingRepository).findById(id);
        verify(bookingRepository, never()).delete(any());
    }
}
