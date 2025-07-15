package com.mySkin.repository;


import com.mySkin.entities.Ingredient;
import com.mySkin.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIngredientsContaining(Ingredient ingredient);
}
