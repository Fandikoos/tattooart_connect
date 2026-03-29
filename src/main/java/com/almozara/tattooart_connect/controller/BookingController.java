package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.BookingDto;
import com.almozara.tattooart_connect.service.booking.BookingService;
import com.almozara.tattooart_connect.util.helper.AuthorityHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Booking Controller", description = "Booking calendar operations")
@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + BookingController.URL)
@RequiredArgsConstructor
public class BookingController {

    public static final String URL = "/booking";

    private final BookingService bookingService;

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<BookingDto> create(@RequestBody @Valid BookingDto bookingDto) {
        return new ResponseEntity<>(bookingService.create(bookingDto), HttpStatus.CREATED);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @GetMapping
    public ResponseEntity<List<BookingDto>> findAll() {
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @GetMapping("/{idBooking}")
    public ResponseEntity<BookingDto> findById(@PathVariable Long idBooking) {
        return new ResponseEntity<>(bookingService.findById(idBooking), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @GetMapping("/studio/{idStudio}")
    public ResponseEntity<List<BookingDto>> findByIdStudio(@PathVariable Long idStudio) {
        return new ResponseEntity<>(bookingService.findByIdStudio(idStudio), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @GetMapping("/artist/{idArtist}")
    public ResponseEntity<List<BookingDto>> findByIdArtist(@PathVariable Long idArtist) {
        return new ResponseEntity<>(bookingService.findByIdArtist(idArtist), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PutMapping("/{idBooking}")
    public ResponseEntity<BookingDto> update(@PathVariable Long idBooking, @RequestBody @Valid BookingDto bookingDto) {
        return new ResponseEntity<>(bookingService.update(idBooking, bookingDto), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @DeleteMapping("/{idBooking}")
    public ResponseEntity<Void> delete(@PathVariable Long idBooking) {
        bookingService.delete(idBooking);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}