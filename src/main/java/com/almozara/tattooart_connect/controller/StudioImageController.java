package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.StudioImageDto;
import com.almozara.tattooart_connect.service.studioimage.StudioImageService;
import com.almozara.tattooart_connect.util.helper.AuthorityHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<StudioImageDto> create(@RequestBody @Valid StudioImageDto studioImageDto){
        StudioImageDto studioImage = studioImageService.create(studioImageDto);
        return new ResponseEntity<>(studioImage, HttpStatus.CREATED);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @DeleteMapping("/{idStudioImage}")
    public ResponseEntity<Void> delete(@PathVariable  Long idStudioImage){
        studioImageService.delete(idStudioImage);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PutMapping("/{idStudioImage}")
    public ResponseEntity<Void> update(@PathVariable Long idStudioImage, @RequestBody @Valid StudioImageDto studioImageDto){
        studioImageService.update(idStudioImage, studioImageDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
