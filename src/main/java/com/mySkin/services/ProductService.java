package com.mySkin.services;

import com.mySkin.dtos.ProductDTO;
import com.mySkin.dtos.ReviewDTO;
import com.mySkin.entities.Product;
import com.mySkin.entities.Review;
import com.mySkin.entities.User;
import com.mySkin.repository.ProductRepository;
import com.mySkin.resources.ProductResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> list = productRepository.findAll(pageable);
        return list.map(p -> new ProductDTO(p)
                .add(linkTo(methodOn(ProductResource.class).findAll(null)).withSelfRel())
                .add(linkTo(methodOn(ProductResource.class).findById(p.getId())).withRel("One product")));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        ProductDTO productDTO =  new ProductDTO(product.get());

        return productDTO
                .add(linkTo(methodOn(ProductResource.class).findById(id)).withSelfRel())
                .add(linkTo(methodOn(ProductResource.class).findAll(null)).withRel("All Products"))
                .add(linkTo(methodOn(ProductResource.class).update(id, new ProductDTO(productDTO))).withRel("Update product"))
                .add(linkTo(methodOn(ProductResource.class).delete(id)).withRel("Delete"));
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

        return new ProductDTO(entity)
                .add(linkTo(methodOn(ProductResource.class).findById(productDTO.getId())).withRel("Product"))
                .add(linkTo(methodOn(ProductResource.class).findAll(null)).withRel("All Products"))
                .add(linkTo(methodOn(ProductResource.class).update(productDTO.getId(), new ProductDTO(productDTO))).withRel("Update product"))
                .add(linkTo(methodOn(ProductResource.class).delete(productDTO.getId())).withRel("Delete"));

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

        return new ProductDTO(entity)
                .add(linkTo(methodOn(ProductResource.class).findById(entity.getId())).withRel("One Product"))
                .add(linkTo(methodOn(ProductResource.class).delete(null)).withRel("Delete Products"));
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllIngredients() {
        productRepository.deleteAll();
    }
}
