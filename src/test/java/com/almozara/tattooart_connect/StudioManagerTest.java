package com.almozara.tattooart_connect;


import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.mapper.StudioMapper;
import com.almozara.tattooart_connect.repository.StudioRepository;
import com.almozara.tattooart_connect.service.studio.StudioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
    void testCreate(){
        // 1. Preparar datos de prueba
        StudioDto createStudioDto = createStudioDto(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3);
        StudioEntity createStudioEntity = createStudioEntity(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3);
        StudioDto expectedCreateStudioDto = createStudioDto(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3);

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
    void testUpdate(){
        StudioDto updateStudioDto = createStudioDto(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3);;
        StudioEntity existingStudioEntity = createStudioEntity(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3);;
        StudioEntity updatedStudioEntity = createStudioEntity(1L, "Prueba Estudio 1", "Avenida Inventada, 1", 3);

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
    void testDelete(){
        StudioEntity deleteStudio = new StudioEntity();
        deleteStudio.setIdStudio(1L);
        deleteStudio.setName("Estudio a eliminar");

        when(studioRepository.findById(deleteStudio.getIdStudio())).thenReturn(Optional.of(deleteStudio));
        doNothing().when(studioRepository).delete(deleteStudio);

        studioService.delete(deleteStudio.getIdStudio());

        verify(studioRepository).findById(deleteStudio.getIdStudio());
        verify(studioRepository).delete(deleteStudio);
    }

    private StudioDto createStudioDto(Long id, String name, String address, int rating) {
        StudioDto dto = new StudioDto();
        dto.setIdStudio(id);
        dto.setName(name);
        dto.setAddress(address);
        dto.setRating(rating);
        return dto;
    }

    private StudioEntity createStudioEntity(Long id, String name, String address, int rating) {
        StudioEntity entity = new StudioEntity();
        entity.setIdStudio(id);
        entity.setName(name);
        entity.setAddress(address);
        entity.setRating(rating);
        return entity;
    }
}
