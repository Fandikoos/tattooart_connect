package com.almozara.tattooart_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioDto {

    private Long idStudio;
    private String name;
    private String address;
    private long latitud;
    private long longitud;
    private int rating;
    private List<ArtistDto> artists;
    private List<StudioImageDto> images;
}
