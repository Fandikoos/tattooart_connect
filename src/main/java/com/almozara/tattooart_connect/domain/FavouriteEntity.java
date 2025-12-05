package com.almozara.tattooart_connect.domain;


import com.almozara.tattooart_connect.security.domain.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = FavouriteEntity.TABLE_NAME)
public class FavouriteEntity {

    public static final String TABLE_NAME = "ET_FAVOURITE";

    public static final String ID_COLUMN = "ID_FAVOURITE";
    public static final String ID_USER_COLUMN = "ID_USER";
    public static final String ID_STUDIO_COLUMN = "ID_STUDIO";
    public static final String CREATED_AT_COLUMN = "CREATED_AT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Long idFavourite;

    @ManyToOne
    @JoinColumn(name = ID_USER_COLUMN, referencedColumnName = UserEntity.ID_USER_COLUMN)
    @ToString.Exclude
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = ID_STUDIO_COLUMN, referencedColumnName = StudioEntity.ID_STUDIO_COLUMN)
    @ToString.Exclude
    private StudioEntity studioEntity;

    @Column(name = CREATED_AT_COLUMN)
    private LocalDateTime createdAt = LocalDateTime.now();
}
