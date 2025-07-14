package com.mySkin.resources;


import com.mySkin.dtos.SkinDTO;
import com.mySkin.services.SkinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/skin")
public class SkinResource {

    @Autowired
    private SkinService skinService;


    //findAll
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Get all skins",
            summary = "Get all skins",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<SkinDTO>> findAll(Pageable pageable) {
        Page<SkinDTO> skins = skinService.findAll(pageable);
        return ResponseEntity.ok(skins);
    }

    //findById
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Get a skin",
            summary = "Get a skin by its id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SkinDTO> findById(@PathVariable Long id) {
        SkinDTO skin = skinService.findById(id);
        return ResponseEntity.ok(skin);
    }

    //insert
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Create a new room",
            summary = "Create a new room",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
            }
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<SkinDTO> insert(@RequestBody SkinDTO skinDTO) {

        skinDTO = skinService.insert(skinDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(skinDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(skinDTO);
    }

    //update
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Update a skin",
            summary = "Update a skin",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SkinDTO> update(@PathVariable Long id, @RequestBody SkinDTO skinDTO) {

        skinDTO = skinService.update(skinDTO, id);

        return ResponseEntity.ok().body(skinDTO);
    }

    //delete
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Delete a skin",
            summary = "Delete a skin",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        skinService.delete(id);

        return ResponseEntity.noContent().build();
    }

    //delete all
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            description = "Delete all skins",
            summary = "Delete all skins",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteAllSkins() {

        skinService.deleteAllSkins();
        return ResponseEntity.noContent().build();
    }
}
