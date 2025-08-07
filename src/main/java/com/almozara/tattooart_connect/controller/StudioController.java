package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.service.studio.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + StudioController.URL)
public class StudioController {

    public static final String URL = "/studio";

    @Autowired
    private StudioService studioService;
    // Ejemplo para poner roles a rutas, en este caso seria cualquiera de los roles, pero quiero que esta ruta no necesite roles
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<StudioDto>> getAll(){
        return new ResponseEntity<>(studioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idStudio}")
    public ResponseEntity<StudioDto> findById(@PathVariable Long idStudio){
        return new ResponseEntity<>(studioService.findById(idStudio), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/update/{idStudio}")
    public ResponseEntity<Void> update(@PathVariable Long idStudio, @RequestBody StudioDto studioDto){
        studioService.update(idStudio, studioDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
