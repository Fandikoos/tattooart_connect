package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.ImageDto;
import com.almozara.tattooart_connect.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + ImageController.URL)
@RequiredArgsConstructor
public class ImageController {

    public static final String URL = "/studios/{idStudio}/images";

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageDto> upload(@PathVariable Long idStudio, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(imageService.uploadImage(idStudio, file), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> findByStudioId(@PathVariable Long idStudio) {
        return new ResponseEntity<>(imageService.getImagesByStudio(idStudio), HttpStatus.OK);
    }

    @DeleteMapping("/{idImage}")
    public ResponseEntity<Void> delete(@PathVariable Long idImage) {
        imageService.deleteImage(idImage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
