package com.mySkin.resources;

import com.mySkin.dtos.IngredientDTO;
import com.mySkin.services.IngredientService;
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
@RequestMapping(value = "/ingredient")
public class IngredientResource {

    @Autowired
    private IngredientService ingredientService;

    //find all
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Get all ingredients",
            summary = "Get all ingredients",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<IngredientDTO>> findAll(Pageable pageable) {
        Page<IngredientDTO> reviews = ingredientService.findAll(pageable);
        return ResponseEntity.ok(reviews);
    }


    //findById
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(
            description = "Get a ingredient",
            summary = "Get a ingredient by its id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<IngredientDTO> findById(@PathVariable Long id) {
        IngredientDTO ingredient = ingredientService.findById(id);
        return ResponseEntity.ok(ingredient);
    }


    //insert
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            description = "Create a new ingredient",
            summary = "Create a new ingredient",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
            }
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<IngredientDTO> insert(@RequestBody IngredientDTO ingredientDTO) {

        ingredientDTO = ingredientService.insert(ingredientDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ingredientDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(ingredientDTO);
    }


    //update
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            description = "Update a ingredient",
            summary = "Update a ingredient",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<IngredientDTO> update(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {

        ingredientDTO = ingredientService.update(ingredientDTO, id);

        return ResponseEntity.ok().body(ingredientDTO);
    }


    //delete
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            description = "Delete a ingredient",
            summary = "Delete a ingredient",
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

        ingredientService.delete(id);

        return ResponseEntity.noContent().build();
    }


    //delete all
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            description = "Delete all ingredients",
            summary = "Delete all ingredients",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteAllIngredients() {

        ingredientService.deleteAllIngredients();
        return ResponseEntity.noContent().build();
    }
}
