package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.StudioImageDto;
import com.almozara.tattooart_connect.service.studioimage.StudioImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + StudioImageController.URL)
public class StudioImageController {

    public static final String URL = "/studioimage";

    @Autowired
    private StudioImageService studioImageService;

    @GetMapping
    public ResponseEntity<List<StudioImageDto>> getAll(){
        return new ResponseEntity<>(studioImageService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idStudioImage}")
    public ResponseEntity<StudioImageDto> findById(@PathVariable Long idStudioImage){
        return new ResponseEntity<>(studioImageService.findById(idStudioImage), HttpStatus.OK);

    }
}
