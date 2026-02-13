package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.FavouriteEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.FavouriteDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.repository.StudioRepository;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FavouriteMapper {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected StudioRepository studioRepository;

    // Entity -> DTO
    @Mapping(source = "userEntity.idUser", target = "idUser")
    @Mapping(source = "studioEntity.idStudio", target = "idStudio")
    public abstract FavouriteDto transferToDto(FavouriteEntity favouriteEntity);

    // DTO -> Entity
    @Mapping(source = "idUser", target = "userEntity")
    @Mapping(source = "idStudio", target = "studioEntity")
    public abstract FavouriteEntity transferToEntity(FavouriteDto favouriteDto);

    public abstract List<FavouriteDto> transferToDtoList(List<FavouriteEntity> favouriteEntities);

    protected UserEntity map(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    protected StudioEntity mapStudio(Long id) {
        return studioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Studio not found"));
    }
}

