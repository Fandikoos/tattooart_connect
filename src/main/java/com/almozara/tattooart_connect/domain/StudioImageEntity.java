package com.almozara.tattooart_connect.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = StudioImageEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class StudioImageEntity {

    public static final String TABLE_NAME = "ET_STUDIO_IMAGE";

    private static final String ID_STUDIO_IMAGE_COLUMN = "ID_STUDIO_IMAGE";
    private static final String IMAGE_URL_COLUMN = "IMAGE_URL";
    private static final String NAME_COLUMN = "NAME";
    private static final String DESCRIPTION_COLUMN = "DESCRIPTION";
    private static final String ID_STUDIO = "ID_STUDIO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_STUDIO_IMAGE_COLUMN)
    private Long id;

    @Column(name = IMAGE_URL_COLUMN)
    private String imageUrl;

    @Column(name = NAME_COLUMN)
    private String name;

    @Column(name = DESCRIPTION_COLUMN)
    private String description;

    @ManyToOne
    @JoinColumn(name = ID_STUDIO)
    private StudioEntity studio;

}
