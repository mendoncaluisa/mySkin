package com.mySkin.dtos;

import com.mySkin.entities.Ingredient;
import com.mySkin.entities.Product;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import org.springframework.hateoas.RepresentationModel;


@Data
public class ProductDTO extends RepresentationModel<ProductDTO> {

    private Long id;

    private String name;

    private String description;

    private String company;

    private Double size;

    private Set<Ingredient> ingredients = new HashSet<>();

    public ProductDTO(Long id, String name, String description, String company, Double size) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.company = company;
        this.size = size;
    }

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.company = product.getCompany();
        this.size = product.getSize();
    }

    public ProductDTO(ProductDTO productDTO) {
        this.id = productDTO.id;
        this.name = productDTO.name;
        this.description = productDTO.description;
        this.company = productDTO.company;
        this.size = productDTO.size;
    }
}
