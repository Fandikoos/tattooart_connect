package com.almozara.tattooart_connect.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    public ModelMapperUtil() {
        this.modelMapper = new ModelMapper();
        // Configuracion adicionales si fuera necesario
        this.modelMapper.getConfiguration().setSkipNullEnabled(true); // Ignora campos null en el objeto origen al mapear
    }

    // Convertir entidad en dto
    public <D, E> D mapEntityToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    // Convertir dto en entidad
    public <E, D> E mapDtoToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
