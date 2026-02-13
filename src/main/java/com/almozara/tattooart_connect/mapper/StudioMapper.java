package com.almozara.tattooart_connect.mapper;

import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.global.exceptions.NotFoundException;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                ArtistMapper.class,
                ImageMapper.class,
                ReviewMapper.class,
        })
public abstract class StudioMapper {

    @Autowired
    protected UserRepository userRepository;

    // DTO -> Entity
    @Mapping(source = "idUser", target = "user", qualifiedByName = "studioMapUserFromId")
    public abstract StudioEntity transferToEntity(StudioDto dto);

    // Conversión idUserDto -> userEntity
    // MapStruct sabe que en algun momento se va a recibir un long perteneciente al idUser que lo tiene que mapear a entidad, entocnes buscara en este metodo
    // da igual como se llame, lo importante es el parametro que recibe y lo que devuelve, mapstrcut se encarga del resto, ejemplo de eliminar un esdtuio desde angular
    @Named("studioMapUserFromId")
    protected UserEntity map(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    // Entity -> DTO
    @Mapping(source = "user.idUser", target = "idUser")
    public abstract StudioDto transferToDto(StudioEntity entity);

    public abstract List<StudioDto> transferToDtoList(List<StudioEntity> entities);
}
