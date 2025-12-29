package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.FavouriteDto;
import com.almozara.tattooart_connect.service.favourite.FavouriteService;
import com.almozara.tattooart_connect.util.helper.AuthorityHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + FavouriteController.URL)
@RequiredArgsConstructor
public class FavouriteController {

    public static final String URL = "/favourite";

    private final FavouriteService favouriteService;

    @GetMapping
    public ResponseEntity<List<FavouriteDto>> getAll() {
        List<FavouriteDto> favourites = favouriteService.getAll();
        return new ResponseEntity<>(favourites, HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_USER)
    @GetMapping("user/{idUser}")
    public ResponseEntity<List<FavouriteDto>> findByIdUser(@PathVariable Long idUser) {
        List<FavouriteDto> favsByUser = favouriteService.findByIdUser(idUser);
        return new ResponseEntity<>(favsByUser, HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_USER)
    @PostMapping
    public ResponseEntity<FavouriteDto> addFavourite(@RequestBody @Valid FavouriteDto favouriteDto) {
        FavouriteDto fav = favouriteService.addFavourite(favouriteDto);
        return new ResponseEntity<>(fav, HttpStatus.CREATED);
    }

    @PreAuthorize(AuthorityHelper.ROLE_USER)
    @DeleteMapping("/{idFavourite}")
    public ResponseEntity<Void> addFavourite(@PathVariable Long idFavourite) {
        favouriteService.deleteFavourite(idFavourite);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
