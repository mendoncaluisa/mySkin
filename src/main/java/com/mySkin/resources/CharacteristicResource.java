package com.mySkin.resources;

import com.mySkin.dtos.CharacteristicDTO;
import com.mySkin.dtos.ReviewDTO;
import com.mySkin.services.CharacteristicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/characteristic")
public class CharacteristicResource {

    @Autowired
    private CharacteristicService characteristicService;


    //find all
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Get all Characteristic]",
            summary = "Get all Characteristic",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CharacteristicDTO>> findAll(Pageable pageable) {
        Page<CharacteristicDTO> reviews = characteristicService.findAll(pageable);
        return ResponseEntity.ok(reviews);
    }

    //findById
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Get a Characteristic",
            summary = "Get a Characteristic by its id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CharacteristicDTO> findById(@PathVariable Long id) {
        CharacteristicDTO review = characteristicService.findById(id);
        return ResponseEntity.ok(review);
    }


    //insert
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Create a new Characteristic",
            summary = "Create a new Characteristic",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
            }
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<CharacteristicDTO> insert(@RequestBody CharacteristicDTO characteristicDTO) {

        characteristicDTO = characteristicService.insert(characteristicDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(characteristicDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(characteristicDTO);
    }


    //update
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Update a Characteristic",
            summary = "Update a Characteristic",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CharacteristicDTO> update(@PathVariable Long id, @RequestBody CharacteristicDTO characteristicDTO) {

        characteristicDTO = characteristicService.update(characteristicDTO, id);

        return ResponseEntity.ok().body(characteristicDTO);
    }


    //delete
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Delete a Characteristic",
            summary = "Delete a Characteristic",
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

        characteristicService.delete(id);

        return ResponseEntity.noContent().build();
    }


    //delete all
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            description = "Delete all Characteristic",
            summary = "Delete all Characteristic",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteAllRooms() {

        characteristicService.deleteAllCharacteristics();
        return ResponseEntity.noContent().build();
    }
}
