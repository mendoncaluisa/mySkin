package com.mySkin.resources;

import com.mySkin.dtos.ProductDTO;
import com.mySkin.dtos.ReviewDTO;
import com.mySkin.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    //find all
    @Operation(
            description = "Get all product",
            summary = "Get all product",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200")
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> reviews = productService.findAll(pageable);
        return ResponseEntity.ok(reviews);
    }


    //findById
    @Operation(
            description = "Get a product",
            summary = "Get a product by its id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);
        return ResponseEntity.ok(product);
    }


    //insert
    @Operation(
            description = "Create a new product",
            summary = "Create a new product",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
            }
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO productDTO) {

        productDTO = productService.insert(productDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(productDTO);
    }


    //update
    @Operation(
            description = "Update a product",
            summary = "Update a product",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404")
            }
    )
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

        productDTO = productService.update(productDTO, id);

        return ResponseEntity.ok().body(productDTO);
    }


    //delete
    @Operation(
            description = "Delete a product",
            summary = "Delete a product",
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

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }


    //delete all
    @Operation(
            description = "Delete all products",
            summary = "Delete all products",
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

        productService.deleteAllRooms();
        return ResponseEntity.noContent().build();
    }
}
