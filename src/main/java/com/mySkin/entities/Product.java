package com.mySkin.entities;

import com.mySkin.dtos.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String company;

    private Double size;

    @ManyToMany
    @JoinTable(name = "product_ingredient",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    public Product(Long id, String name, String description, String company, Double size, Set<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.company = company;
        this.size = size;
        this.ingredients = ingredients;
    }

    public Product() {
    }

    public Product(ProductDTO productDTO) {
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.company = productDTO.getCompany();
        this.size = productDTO.getSize();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(ingredients, product.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ingredients);
    }
}
