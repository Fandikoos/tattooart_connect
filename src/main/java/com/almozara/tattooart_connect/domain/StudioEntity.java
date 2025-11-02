package com.almozara.tattooart_connect.domain;

import com.almozara.tattooart_connect.security.domain.UserEntity;
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

    public static final String ID_STUDIO_COLUMN = "ID_STUDIO";
    public static final String NAME_COLUMN = "NAME";
    public static final String ADDRESS_COLUMN = "ADDRESS";
    public static final String LATITUD_COLUMN = "LATITUD";
    public static final String LONGITUD_COLUMN = "LONGITUD";
    public static final String RATING_COLUMN = "RATING";
    public static final String LOGO_COLUMN = "LOGO";
    public static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    public static final String OPEN_SCHEDULE_COLUMN = "OPEN_SCHEDULE";
    public static final String CLOSE_SCHEDULE_COLUMN = "CLOSE_SCHEDULE";
    public static final String USER_COLUMN = "ID_USER";

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

    @OneToMany(mappedBy = "tattooStudio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ArtistEntity> artists;

    @OneToMany(mappedBy = "tattooStudio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudioImageEntity> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_COLUMN, referencedColumnName = UserEntity.ID_USER_COLUMN)
    private UserEntity user;
}
