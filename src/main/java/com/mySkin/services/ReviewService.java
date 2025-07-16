package com.mySkin.services;


import com.mySkin.dtos.ReviewDTO;
import com.mySkin.entities.Product;
import com.mySkin.entities.Review;
import com.mySkin.entities.User;
import com.mySkin.repository.ReviewRepository;
import com.mySkin.resources.ProductResource;
import com.mySkin.resources.ReviewResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Page<ReviewDTO> findAll(Pageable pageable) {
        Page<Review> list = reviewRepository.findAll(pageable);
        return list.map(s -> new ReviewDTO(s)
                .add(linkTo(methodOn(ReviewResource.class).findAll(null)).withSelfRel())
                .add(linkTo(methodOn(ReviewResource.class).findById(s.getId())).withRel("One review")));

    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        ReviewDTO reviewDTO =  new ReviewDTO(review.get());

        return reviewDTO
                .add(linkTo(methodOn(ReviewResource.class).findById(review.get().getId())).withSelfRel())
                .add(linkTo(methodOn(ReviewResource.class).findAll(null)).withRel("All Reviews"))
                .add(linkTo(methodOn(ReviewResource.class).update(review.get().getId(), new ReviewDTO(review.get()))).withRel("Update Review"))
                .add(linkTo(methodOn(ReviewResource.class).delete(review.get().getId())).withRel("Delete"));
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO reviewDTO) {
        Review entity = new Review();

        entity.setRate(reviewDTO.getRate());
        entity.setNegative(reviewDTO.getNegative());
        entity.setPositive(reviewDTO.getPositive());
        entity.setUser(new User(reviewDTO.getUser()));
        entity.setProduct(new Product(reviewDTO.getProduct()));
        reviewRepository.save(entity);

        return new ReviewDTO(entity)
                .add(linkTo(methodOn(ReviewResource.class).findById(entity.getId())).withRel("Review"))
                .add(linkTo(methodOn(ReviewResource.class).findAll(null)).withRel("All Reviews"))
                .add(linkTo(methodOn(ReviewResource.class).update(entity.getId(), new ReviewDTO(entity))).withRel("Update Review"))
                .add(linkTo(methodOn(ReviewResource.class).delete(entity.getId())).withRel("Delete"));

    }

    @Transactional
    public ReviewDTO update(ReviewDTO reviewDTO, Long id) {
        Review entity = reviewRepository.getReferenceById(id);

        entity.setRate(reviewDTO.getRate());
        entity.setNegative(reviewDTO.getNegative());
        entity.setPositive(reviewDTO.getPositive());
        entity.setUser(new User(reviewDTO.getUser()));
        entity.setProduct(new Product(reviewDTO.getProduct()));
        entity = reviewRepository.save(entity);

        return new ReviewDTO(entity)
                .add(linkTo(methodOn(ReviewResource.class).findById(entity.getId())).withRel("Review"))
                .add(linkTo(methodOn(ReviewResource.class).delete(null)).withRel("Delete Reviews"));
    }

    @Transactional
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllRooms() {
        reviewRepository.deleteAll();
    }

}
