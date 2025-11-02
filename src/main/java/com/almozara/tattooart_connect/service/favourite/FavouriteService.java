package com.almozara.tattooart_connect.service.favourite;

import com.almozara.tattooart_connect.dto.FavouriteDto;

import java.util.List;

public interface FavouriteService {
    List<FavouriteDto> getAll();
    FavouriteDto addFavourite(FavouriteDto favouriteDto);
    void deleteFavourite(Long idFavourite);
    List<FavouriteDto> findByIdUser(Long idUser);
}
