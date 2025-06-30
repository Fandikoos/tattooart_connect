package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.service.studio.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = StudioController.URL)
public class StudioController {

    public static final String URL = "/tattoo/studio";

    @Autowired
    private StudioService studioService;

    @GetMapping
    public ResponseEntity<List<StudioDto>> getAll(){
        return new ResponseEntity<>(studioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idStudio}")
    public ResponseEntity<StudioDto> findById(@PathVariable Long idStudio){
        return new ResponseEntity<>(studioService.findById(idStudio), HttpStatus.OK);

    }

    @PutMapping("/update/{idStudio}")
    public ResponseEntity<Void> update(@PathVariable Long idStudio, @RequestBody StudioDto studioDto){
        studioService.update(idStudio, studioDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
