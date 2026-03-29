package com.almozara.tattooart_connect.service.booking;

import com.almozara.tattooart_connect.domain.BookingEntity;
import com.almozara.tattooart_connect.dto.BookingDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.mapper.BookingMapper;
import com.almozara.tattooart_connect.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public BookingDto create(BookingDto bookingDto) {
        BookingEntity entity = bookingMapper.transferToEntity(bookingDto);
        BookingEntity saved = bookingRepository.save(entity);
        return bookingMapper.transferToDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDto> findAll() {
        return bookingMapper.transferToDtoList(bookingRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDto> findByIdStudio(Long idStudio) {
        return bookingMapper.transferToDtoList(bookingRepository.findByTattooStudioIdStudio(idStudio));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingDto> findByIdArtist(Long idArtist) {
        return bookingMapper.transferToDtoList(bookingRepository.findByArtistIdArtist(idArtist));
    }

    @Override
    @Transactional(readOnly = true)
    public BookingDto findById(Long idBooking) {
        BookingEntity entity = bookingRepository.findById(idBooking)
                .orElseThrow(() -> new NotFoundException("Booking with id " + idBooking + " not found"));
        return bookingMapper.transferToDto(entity);
    }

    @Override
    @Transactional
    public BookingDto update(Long idBooking, BookingDto bookingDto) {
        BookingEntity entity = bookingRepository.findById(idBooking)
                .orElseThrow(() -> new NotFoundException("Booking with id " + idBooking + " not found"));

        entity.setTitle(bookingDto.getTitle());
        entity.setDescription(bookingDto.getDescription());
        entity.setStartDateTime(bookingDto.getStartDateTime());
        entity.setEndDateTime(bookingDto.getEndDateTime());
        entity.setType(bookingDto.getType());
        entity.setStatus(bookingDto.getStatus());
        entity.setClientName(bookingDto.getClientName());
        entity.setClientPhone(bookingDto.getClientPhone());
        entity.setClientEmail(bookingDto.getClientEmail());
        entity.setColor(bookingDto.getColor());

        return bookingMapper.transferToDto(bookingRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long idBooking) {
        BookingEntity entity = bookingRepository.findById(idBooking)
                .orElseThrow(() -> new NotFoundException("Booking with id " + idBooking + " not found"));
        bookingRepository.delete(entity);
    }
}