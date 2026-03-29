package com.almozara.tattooart_connect.service.booking;

import com.almozara.tattooart_connect.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto create(BookingDto bookingDto);

    List<BookingDto> findAll();

    List<BookingDto> findByIdStudio(Long idStudio);

    List<BookingDto> findByIdArtist(Long idArtist);

    BookingDto findById(Long idBooking);

    BookingDto update(Long idBooking, BookingDto bookingDto);

    void delete(Long idBooking);
}