package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.service.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ArtistController.URL)
public class ArtistController {

    public static final String URL = "/tattoo/artist";

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtist(){
        return new ResponseEntity<>(artistService.findAllArtist(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArtistDto> create(@RequestBody ArtistDto artist){
        return new ResponseEntity<>(artistService.createArtist(artist), HttpStatus.CREATED);
    }

    @GetMapping("/{idArtist}")
    public ResponseEntity<ArtistDto> findById(@PathVariable Long idArtist){
        return new ResponseEntity<>(artistService.findById(idArtist), HttpStatus.OK);

    }
}
