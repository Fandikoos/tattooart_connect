package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.dto.CreateUserDto;
import com.almozara.tattooart_connect.security.dto.ProfileUserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserDto transferToCreateUserDto(UserEntity userEntity);
    UserEntity transferCreateUserDtoToEntity(CreateUserDto createUserDto);
    List<StudioDto> transferToDtoList(List<StudioEntity> studioEntities);
    ProfileUserDto transferProfileUserDto(UserEntity userEntity);
}
