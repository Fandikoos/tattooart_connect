package com.almozara.tattooart_connect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = StudioEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class StudioEntity {

    public static final String TABLE_NAME = "ET_STUDIO";

    private static final String ID_STUDIO_COLUMN = "ID_STUDIO";
    private static final String NAME_COLUMN = "NAME";
    private static final String ADDRESS_COLUMN = "ADDRESS";
    private static final String LATITUD_COLUMN = "LATITUD";
    private static final String LONGITUD_COLUMN = "LONGITUD";
    private static final String RATING_COLUMN = "RATING";
    private static final String LOGO_COLUMN = "LOGO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_STUDIO_COLUMN)
    private Long idStudio;

    @Column(name = NAME_COLUMN)
    private String name;

    @Column(name = ADDRESS_COLUMN)
    private String address;

    @Column(name = LATITUD_COLUMN)
    private long latitud;

    @Column(name = LONGITUD_COLUMN)
    private long longitud;

    @Column(name = RATING_COLUMN)
    private int rating;

    @Column
    private String logo;

    @OneToMany(mappedBy = "tattooStudio", cascade = CascadeType.ALL)
    private List<ArtistEntity> artists;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudioImageEntity> images;
}
