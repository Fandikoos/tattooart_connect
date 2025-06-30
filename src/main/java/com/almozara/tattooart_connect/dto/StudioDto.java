package com.almozara.tattooart_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioDto {

    private Long idStudio;
    private String name;
    private String address;
    private float latitud;
    private float longitud;
    private int rating;
    private String description;
    private String logo;
    private LocalTime openSchedule;
    private LocalTime closeSchedule;
    private List<ArtistDto> artists;
    private List<StudioImageDto> images;
}
