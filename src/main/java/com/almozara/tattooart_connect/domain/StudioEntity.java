package com.almozara.tattooart_connect.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    private static final String OPEN_SCHEDULE_COLUMN = "OPEN_SCHEDULE";
    private static final String CLOSE_SCHEDULE_COLUMN = "CLOSE_SCHEDULE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_STUDIO_COLUMN)
    private Long idStudio;

    @Column(name = NAME_COLUMN)
    private String name;

    @Column(name = ADDRESS_COLUMN)
    private String address;

    @Column(name = LATITUD_COLUMN)
    private float latitud;

    @Column(name = LONGITUD_COLUMN)
    private float longitud;

    @Column(name = RATING_COLUMN)
    private int rating;

    @Column(name = DESCRIPTION_COLUMN)
    private String description;

    @Column(name = OPEN_SCHEDULE_COLUMN)
    private LocalTime openSchedule;

    @Column(name = CLOSE_SCHEDULE_COLUMN)
    private LocalTime closeSchedule;

    @Column(name = LOGO_COLUMN)
    private String logo;

    @OneToMany(mappedBy = "tattooStudio", cascade = CascadeType.ALL)
    private List<ArtistEntity> artists;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudioImageEntity> images;
}
