package com.almozara.tattooart_connect.dto;


import com.almozara.tattooart_connect.domain.StudioEntity;
import com.almozara.tattooart_connect.security.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteDto {
    private Long idFavourite;
    private Long idUser;
    private Long idStudio;
    private LocalDateTime createdAt = LocalDateTime.now();
}
