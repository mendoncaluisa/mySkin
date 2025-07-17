package com.mySkin.services;


import com.mySkin.dtos.ReviewDTO;
import com.mySkin.entities.Product;
import com.mySkin.entities.Review;
import com.mySkin.entities.User;
import com.mySkin.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Transactional(readOnly = true)
    public Page<ReviewDTO> findAll(Pageable pageable) {
        Page<Review> list = reviewRepository.findAll(pageable);
        return list.map(s -> new ReviewDTO(s));
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        ReviewDTO reviewDTO =  new ReviewDTO(review.get());

        return reviewDTO;
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO reviewDTO) {
        Review entity = new Review();

        entity.setRate(reviewDTO.getRate());
        entity.setNegative(reviewDTO.getNegative());
        entity.setPositive(reviewDTO.getPositive());

        // Buscar entidades persistidas pelo ID
        User user = userService.findEntityById(reviewDTO.getUser_id());
        Product product = productService.findEntityById(reviewDTO.getProduct_id());

        entity.setUser(user);
        entity.setProduct(product);
        reviewRepository.save(entity);

        return new ReviewDTO(entity);
    }

    @Transactional
    public ReviewDTO update(ReviewDTO reviewDTO, Long id) {
        Review entity = reviewRepository.getReferenceById(id);

        entity.setRate(reviewDTO.getRate());
        entity.setNegative(reviewDTO.getNegative());
        entity.setPositive(reviewDTO.getPositive());

        // Buscar entidades persistidas pelo ID
        User user = userService.findEntityById(reviewDTO.getUser_id());
        Product product = productService.findEntityById(reviewDTO.getProduct_id());

        entity.setUser(user);
        entity.setProduct(product);
        entity = reviewRepository.save(entity);

        return new ReviewDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review não encontrado");
        }
        reviewRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllRooms() {
        reviewRepository.deleteAll();
    }

}
