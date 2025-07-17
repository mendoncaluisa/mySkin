package com.mySkin.services;

import com.mySkin.dtos.ProductDTO;
import com.mySkin.dtos.ReviewDTO;
import com.mySkin.entities.Product;
import com.mySkin.entities.Review;
import com.mySkin.entities.User;
import com.mySkin.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(p -> new ProductDTO(p));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTO =  new ProductDTO(product.get());

        return productDTO;
    }

    @Transactional(readOnly = true)
    public Product findEntityById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product entity = new Product();

        entity.setCompany(productDTO.getCompany());
        entity.setDescription(productDTO.getDescription());
        entity.setName(productDTO.getName());
        entity.setSize(productDTO.getSize());

        entity.getIngredients().clear();
        entity.getIngredients().addAll(productDTO.getIngredients());

        entity = productRepository.save(entity);

        return new ProductDTO(entity);

    }

    @Transactional
    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product entity = productRepository.getReferenceById(id);

        entity.setCompany(productDTO.getCompany());
        entity.setDescription(productDTO.getDescription());
        entity.setName(productDTO.getName());
        entity.setSize(productDTO.getSize());

        entity.getIngredients().clear();
        entity.getIngredients().addAll(productDTO.getIngredients());

        entity = productRepository.save(entity);

        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllIngredients() {
        productRepository.deleteAll();
    }
}
