package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.ImageEntity;
import com.almozara.tattooart_connect.dto.ImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(source = "tattooStudio.idStudio", target = "idStudio")
    ImageDto transferToDto(ImageEntity imageEntity);

    @Mapping(target = "tattooStudio", ignore = true)
    ImageEntity transferToEntity(ImageDto imageDto);

    List<ImageDto> transferToDtoList(List<ImageEntity> imageEntities);

}
