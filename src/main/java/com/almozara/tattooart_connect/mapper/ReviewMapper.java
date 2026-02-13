package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ReviewEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.ReviewDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.repository.StudioRepository;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected StudioRepository studioRepository;

    // Entity -> DTO
    @Mapping(source = "tattooStudio.idStudio", target = "idTattooStudio")
    @Mapping(source = "user.idUser", target = "idUser")
    @Mapping(source = "user.username", target = "username")
    public abstract ReviewDto transferToDto(ReviewEntity entity);

    // DTO -> Entity
    @Mapping(source = "idTattooStudio", target = "tattooStudio", qualifiedByName = "reviewMapStudioFromId")
    @Mapping(source = "idUser", target = "user", qualifiedByName = "reviewMapUserFromId")
    public abstract ReviewEntity transferToEntity(ReviewDto dto);

    public abstract List<ReviewDto> transferToDtoList(List<ReviewEntity> entities);

    @Named("reviewMapUserFromId")
    protected UserEntity mapUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Named("reviewMapStudioFromId")
    protected StudioEntity mapStudio(Long id) {
        return studioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Studio not found"));
    }
}

