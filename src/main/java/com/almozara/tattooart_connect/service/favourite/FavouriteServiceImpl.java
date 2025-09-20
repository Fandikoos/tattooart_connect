package com.almozara.tattooart_connect.service.favourite;

import com.almozara.tattooart_connect.domain.ArtistEntity;
import com.almozara.tattooart_connect.domain.FavouriteEntity;
import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.dto.FavouriteDto;
import com.almozara.tattooart_connect.mapper.FavouriteMapper;
import com.almozara.tattooart_connect.repository.FavouriteRepository;
import com.almozara.tattooart_connect.repository.StudioRepository;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import com.almozara.tattooart_connect.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    //TODO Cambiar servicios sin autowired y ver como afectan a los test y demas
    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final StudioRepository studioRepository;
    private final FavouriteMapper favouriteMapper;

    @Override
    public List<FavouriteDto> getAll() {
        List<FavouriteEntity> favouriteEntities = favouriteRepository.findAll();
        return favouriteMapper.transferToDtoList(favouriteEntities);
    }

    @Override
    public FavouriteDto addFavourite(FavouriteDto favouriteDto) {
        FavouriteEntity favouriteEntity = favouriteMapper.transferToEntity(favouriteDto);

        Long idUser = favouriteDto.getIdUser();
        if(idUser != null){
            UserEntity user = userRepository.findById(idUser)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            favouriteEntity.setUserEntity(user);
        }
        Long idStudio = favouriteDto.getIdStudio();
        if(idStudio != null){
            StudioEntity studio = studioRepository.findById(idStudio)
                    .orElseThrow(() -> new RuntimeException("Studio not found"));
            favouriteEntity.setStudioEntity(studio);
        }

        FavouriteEntity saved = favouriteRepository.save(favouriteEntity);
        return favouriteMapper.transferToDto(saved);

    }

    @Override
    public void deleteFavourite(Long idFavourite) {
        FavouriteEntity favouriteEntity = favouriteRepository.findById(idFavourite)
                .orElseThrow(() -> new RuntimeException("Favourite Entity not found"));
        favouriteRepository.delete(favouriteEntity);

    }

    @Override
    public List<FavouriteDto> findByIdUser(Long idUser) {
        List<FavouriteEntity> favouriteEntitiesByIdUser = favouriteRepository.findByUserEntityIdUser(idUser);
        if (favouriteEntitiesByIdUser.isEmpty()){
            return new ArrayList<>();
        }
        return favouriteMapper.transferToDtoList(favouriteEntitiesByIdUser);
    }
}
