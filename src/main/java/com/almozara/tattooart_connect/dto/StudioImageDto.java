package com.almozara.tattooart_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioImageDto {

    private Long id;
    private String imageUrl;
    private String name;
    private String description;
    private Long idTattooStudio;
}
