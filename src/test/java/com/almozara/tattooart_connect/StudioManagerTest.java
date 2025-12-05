package com.almozara.tattooart_connect;


import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.mapper.StudioMapper;
import com.almozara.tattooart_connect.repository.StudioRepository;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.service.studio.StudioServiceImpl;
import com.almozara.tattooart_connect.util.enums.RoleEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudioManagerTest {

    // Los mocks son objetos falsos
    @Mock
    private StudioRepository studioRepository;
    @Mock
    private StudioMapper studioMapper;
    // Crea una instancia real de la clase
    @InjectMocks
    private StudioServiceImpl studioService;

    @Test
    void testCreate() {
        // 1. Preparar datos de prueba
        UserEntity user = new UserEntity(1L, "USER_TEST-001", "USER@GMAIL.COM", "PASSWORD_TEST", 654876543, "DESCRIPTION_TEST-001", new ArrayList<>(), Collections.singletonList(RoleEnum.ROLE_ADMIN));
        StudioDto createStudioDto = createStudioDto(null, "Prueba Estudio 1", "Avenida Inventada, 1", 3, 1L);
        StudioEntity createStudioEntity = createStudioEntity(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3, user);
        StudioDto expectedCreateStudioDto = createStudioDto(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3, 1L);

        // 2. Configurar los mocks
        // Le decimos al mock del mapper: "Cuando te pasen este DTO, devuelve esta Entity"
        when(studioMapper.transferToEntity(createStudioDto)).thenReturn(createStudioEntity);
        // Le decimos al mock del repository: "Cuando guardes esta Entity, devuélvela tal cual"
        when(studioRepository.save(createStudioEntity)).thenReturn(createStudioEntity);
        // Le decimos al mock del mapper: "Cuando te pasen esta Entity, devuelve este DTO"
        when(studioMapper.transferToDto(createStudioEntity)).thenReturn(expectedCreateStudioDto);

        // 3. Ejecutar la operación a probar
        StudioDto resultCreateStudioDto = studioService.create(createStudioDto);

        // 4. Verificaciones, hacemos assert porque el create devuelve un dto, el update por ejemplo no
        assertNotNull(resultCreateStudioDto);
        assertEquals(1L, resultCreateStudioDto.getIdStudio());
        assertEquals("Prueba Estudio 1", resultCreateStudioDto.getName());

        // Verificar interacciones con los mocks, que se han mapeado correctamente las cosas, se ha guardado en bbdd, etc
        verify(studioMapper).transferToEntity(createStudioDto);
        verify(studioRepository).save(createStudioEntity);
        verify(studioMapper).transferToDto(createStudioEntity);
    }

    @Test
    void testUpdate() {
        UserEntity user = new UserEntity(1L, "USER_TEST-001", "USER@GMAIL.COM", "PASSWORD_TEST", 654876543, "DESCRIPTION_TEST-001", new ArrayList<>(), Collections.singletonList(RoleEnum.ROLE_ADMIN));
        StudioDto updateStudioDto = createStudioDto(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3, 1L);
        ;
        StudioEntity existingStudioEntity = createStudioEntity(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3, user);
        ;
        StudioEntity updatedStudioEntity = createStudioEntity(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3, user);

        when(studioRepository.findById(existingStudioEntity.getIdStudio())).thenReturn(Optional.of(existingStudioEntity));
        when(studioRepository.save(updatedStudioEntity)).thenReturn(updatedStudioEntity);

        studioService.update(updateStudioDto.getIdStudio(), updateStudioDto);

        // No existen assertEquals porque el update es un metodo que devuelve void

        verify(studioRepository).findById(existingStudioEntity.getIdStudio());
        verify(studioRepository).save(argThat(studioEntity ->
                studioEntity.getIdStudio().equals(updateStudioDto.getIdStudio()) &&
                        studioEntity.getName().equals(updateStudioDto.getName()) &&
                        studioEntity.getAddress().equals(updateStudioDto.getAddress()) &&
                        studioEntity.getRating() == updateStudioDto.getRating()
        ));
    }

    @Test
    void testDelete() {
        StudioEntity deleteStudio = new StudioEntity();
        deleteStudio.setIdStudio(1L);
        deleteStudio.setName("Estudio a eliminar");

        when(studioRepository.findById(deleteStudio.getIdStudio())).thenReturn(Optional.of(deleteStudio));
        doNothing().when(studioRepository).delete(deleteStudio);

        studioService.delete(deleteStudio.getIdStudio());

        verify(studioRepository).findById(deleteStudio.getIdStudio());
        verify(studioRepository).delete(deleteStudio);
    }

    private StudioDto createStudioDto(Long id, String name, String address, int rating, Long idUser) {
        StudioDto dto = new StudioDto();
        dto.setIdStudio(id);
        dto.setName(name);
        dto.setAddress(address);
        dto.setRating(rating);
        dto.setIdUser(idUser);
        return dto;
    }

    private StudioEntity createStudioEntity(Long id, String name, String address, int rating, UserEntity userEntity) {
        StudioEntity entity = new StudioEntity();
        entity.setIdStudio(id);
        entity.setName(name);
        entity.setAddress(address);
        entity.setRating(rating);
        entity.setUser(userEntity);
        return entity;
    }
}
