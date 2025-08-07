package com.almozara.tattooart_connect.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = ArtistEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class ArtistEntity {

    // Nombre tabla
    public static final String TABLE_NAME = "ET_ARTIST";

    // Columnas
    public static final String ID_ARTIST_COLUMN = "ID_ARTIST";
    public static final String NAME_COLUMN = "NAME";
    public static final String SURNAME_COLUMN = "SURNAME";
    public static final String SECOND_SURNAME_COLUMN = "SECOND_SURNAME";
    public static final String EMAIL_COLUMN = "EMAIL";
    public static final String DNI_COLUMN = "DNI";
    public static final String PHONE_COLUMN = "PHONE";
    public static final String IMAGE_ARTIST_COLUMN = "IMAGE_ARTIST";
    public static final String TATTOO_STUDIO_COLUMN = "ID_STUDIO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_ARTIST_COLUMN)
    private Long idArtist;

    @Column(name = ArtistEntity.NAME_COLUMN, nullable = false)
    private String name;

    @Column(name = ArtistEntity.SURNAME_COLUMN)
    private String surname;

    @Column(name = ArtistEntity.SECOND_SURNAME_COLUMN)
    private String secondSurname;

    @Column(name = ArtistEntity.EMAIL_COLUMN)
    private String email;

    @Column(name = ArtistEntity.DNI_COLUMN, nullable = false, unique = true)
    private String dni;

    @Column(name = ArtistEntity.PHONE_COLUMN)
    private String phone;

    @Column(name = IMAGE_ARTIST_COLUMN)
    private String imageArtist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TATTOO_STUDIO_COLUMN)
    private StudioEntity tattooStudio;
}
