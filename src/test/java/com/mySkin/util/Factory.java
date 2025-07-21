package com.mySkin.util;

import com.mySkin.dtos.ProductDTO;
import com.mySkin.entities.Ingredient;
import com.mySkin.entities.Product;

import java.util.HashSet;

public class Factory {

    // Retorna um Product
    public static Product createProduct() {
        Product p = new Product();
        p.setName("Produto Teste");
        p.setDescription("Descrição do produto de teste");
        p.setCompany("Empresa Teste");
        p.setSize(100.0);
        // Exemplo de ingrediente (pode ser ajustado conforme necessário)
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setNome("Ingrediente Teste");
        p.getIngredients().add(ingredient);
        return p;
    }

    // Retorna um ProductDTO
    public static ProductDTO createProductDTO() {
        Product p = createProduct();
        return new ProductDTO(p);
    }
}
