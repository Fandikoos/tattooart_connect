package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.ImageDto;
import com.almozara.tattooart_connect.service.image.ImageService;
import com.almozara.tattooart_connect.service.storage.StorageService;
import com.almozara.tattooart_connect.util.helper.AuthorityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + ImageController.URL)
@RequiredArgsConstructor
public class ImageController {

    public static final String URL = "/studios/{idStudio}/images";

    private final ImageService imageService;
    private final StorageService storageService;

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PostMapping("/upload")
    public ResponseEntity<ImageDto> upload(@PathVariable Long idStudio, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(imageService.uploadImage(idStudio, file), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> findByStudioId(@PathVariable Long idStudio) {
        return new ResponseEntity<>(imageService.getImagesByStudio(idStudio), HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @DeleteMapping("/{idImage}")
    public ResponseEntity<Void> delete(@PathVariable Long idImage) {
        imageService.deleteImage(idImage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Se añade el :.+ para indicar que los nombres al tener puntos, pueden ser jpg, png, jpeg..., luego ya busca el recurso (imagen y lo devuelve), desde angular, desde
    // la etiqueta img es donde se hace esta petición para bsucar este recurso
    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> getStudioImage(@PathVariable Long idStudio, @PathVariable String fileName) {
        Resource image = storageService.load(fileName, idStudio);
        String contentType = URLConnection.guessContentTypeFromName(fileName);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(image);
    }
}
