package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.FavouriteEntity;
import com.almozara.tattooart_connect.dto.ArtistDto;
import com.almozara.tattooart_connect.dto.FavouriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavouriteMapper {

    @Mapping(source = "userEntity.idUser", target = "idUser")
    @Mapping(source = "studioEntity.idStudio", target = "idStudio")
    FavouriteDto transferToDto(FavouriteEntity favouriteEntity);

    @Mapping(source = "idUser", target = "userEntity.idUser")
    @Mapping(source = "idStudio", target = "studioEntity.idStudio")
    FavouriteEntity transferToEntity(FavouriteDto favouriteDto);

    List<FavouriteDto> transferToDtoList(List<FavouriteEntity> favouriteEntities);



}
