package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.service.artist.ArtistService;
import com.almozara.tattooart_connect.util.helper.AuthorityHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Artist Controller", description = "Artist operations")
@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + ArtistController.URL)
@RequiredArgsConstructor
public class ArtistController {

    public static final String URL = "/artist";

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtist() {
        return new ResponseEntity<>(artistService.findAllArtist(), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<ArtistDto> create(@RequestBody @Valid ArtistDto artist) {
        return new ResponseEntity<>(artistService.createArtist(artist), HttpStatus.CREATED);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @GetMapping("artists/{idUser}")
    public ResponseEntity<List<ArtistDto>> findByIdUser(@PathVariable Long idUser) {
        return new ResponseEntity<>(artistService.findByIdUser(idUser), HttpStatus.OK);
    }

    @GetMapping("/{idArtist}")
    public ResponseEntity<ArtistDto> findById(@PathVariable Long idArtist) {
        return new ResponseEntity<>(artistService.findById(idArtist), HttpStatus.OK);
    }

    @GetMapping("studio/{idTattooStudio}")
    public ResponseEntity<List<ArtistDto>> findByIdTattooStudio(@PathVariable Long idTattooStudio) {
        return new ResponseEntity<>(artistService.findByIdStudio(idTattooStudio), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PutMapping("/{idArtist}")
    public ResponseEntity<Void> update(@PathVariable Long idArtist, @RequestBody @Valid ArtistDto artistDto) {
        artistService.update(idArtist, artistDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @DeleteMapping("/{idArtist}")
    public ResponseEntity<Void> delete(@PathVariable Long idArtist) {
        artistService.delete(idArtist);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
