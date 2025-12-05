package com.almozara.tattooart_connect.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = ImageEntity.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {

    public static final String TABLE_NAME = "ET_GALLERY_STUDIO";

    public static final String ID_IMAGE_COLUMN = "ID_IMAGE";
    public static final String STORED_NAME_COLUMN = "STORED_NAME";
    public static final String ORIGINAL_NAME_COLUMN = "ORIGINAL_NAME";
    public static final String URL_COLUMN = "URL";
    public static final String DATE_COLUMN = "DATE";
    public static final String TATTOO_STUDIO_COLUMN = "TATTOO_STUDIO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_IMAGE_COLUMN)
    private Long idImage;

    @Column(name = STORED_NAME_COLUMN, nullable = false)
    private String storedName;

    @Column(name = ORIGINAL_NAME_COLUMN, nullable = false)
    private String originalName;

    @Column(name = URL_COLUMN, nullable = false)
    private String url;

    @CreationTimestamp
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = TATTOO_STUDIO_COLUMN, referencedColumnName = StudioEntity.ID_STUDIO_COLUMN)
    @ToString.Exclude
    private StudioEntity tattooStudio;
}
