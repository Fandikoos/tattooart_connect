package com.almozara.tattooart_connect.controller;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.dto.StudioDto;
import com.almozara.tattooart_connect.record.PageResponse;
import com.almozara.tattooart_connect.service.studio.StudioService;
import com.almozara.tattooart_connect.util.helper.AuthorityHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Studio Controller", description = "Studios operations")
@RestController
@RequestMapping(ApiConfig.API_BASE_PATH + StudioController.URL)
@RequiredArgsConstructor
public class StudioController {

    public static final String URL = "/studio";

    private final StudioService studioService;

    // Ejemplo para poner roles a rutas, en este caso seria cualquiera de los roles, pero quiero que esta ruta no necesite roles
//    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<PageResponse<StudioDto>> getAll(@PageableDefault(sort = "idStudio") Pageable pageable) {
        return new ResponseEntity<>(studioService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idStudio}")
    public ResponseEntity<StudioDto> findById(@PathVariable Long idStudio) {
        return new ResponseEntity<>(studioService.findById(idStudio), HttpStatus.OK);
    }

    @Operation(
            summary = "Get list of studios by filters",
            description = "Get list of studios by name, rating or both filters"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Find Studios"),
            @ApiResponse(responseCode = "404", description = "Studios not found")
    })
    @GetMapping("/search")
    public ResponseEntity<PageResponse<StudioDto>> findByName(@PageableDefault(sort = "idStudio") Pageable pageable,
                                                              @RequestParam(required = false) String name,
                                                              @RequestParam(required = false) BigDecimal minRating,
                                                              @RequestParam(required = false) BigDecimal maxRating) {
        PageResponse<StudioDto> studiosByName = studioService.findByFilters(pageable, name, minRating, maxRating);
        return new ResponseEntity<>(studiosByName, HttpStatus.OK);
    }

    @Operation(
            summary = "Get list of studios by user Id",
            description = "Response with a information of studios by identifier of User"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Find Studios"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/studios/{idUser}")
    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    public ResponseEntity<List<StudioDto>> findByUser(@Parameter(description = "idUser Filter") @PathVariable Long idUser) {
        List<StudioDto> studiosByUser = studioService.findByUser(idUser);
        return new ResponseEntity<>(studiosByUser, HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_USER)
    @GetMapping("/byIdsStudios")
    public ResponseEntity<List<StudioDto>> findByIdsStudios(@RequestParam List<Long> idsStudios) {
        List<StudioDto> studiosByIds = studioService.findByIdsStudios(idsStudios);
        return new ResponseEntity<>(studiosByIds, HttpStatus.OK);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @DeleteMapping("/{idStudio}")
    public ResponseEntity<Void> delete(@PathVariable Long idStudio) {
        studioService.delete(idStudio);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PostMapping
    public ResponseEntity<StudioDto> create(@RequestBody @Valid StudioDto studioDto) {
        return new ResponseEntity<>(studioService.create(studioDto), HttpStatus.CREATED);
    }

    @PreAuthorize(AuthorityHelper.ROLE_ADMIN)
    @PutMapping("/update/{idStudio}")
    public ResponseEntity<Void> update(@PathVariable Long idStudio, @RequestBody @Valid StudioDto studioDto) {
        studioService.update(idStudio, studioDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
