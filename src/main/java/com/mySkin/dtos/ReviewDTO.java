package com.mySkin.dtos;

import com.mySkin.entities.Product;
import com.mySkin.entities.Review;
import lombok.Data;

@Data
public class ReviewDTO {

    private Long id;

    private String positive;

    private String negative;

    private Float rate;

    private UserDTO user = new UserDTO();

    private ProductDTO product = new ProductDTO();

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.positive = review.getPositive();
        this.negative = review.getNegative();
        this.rate = review.getRate();
        this.user = new UserDTO(review.getUser());
        this.product = new ProductDTO(review.getProduct());
    }

    public ReviewDTO(Long id, String positive, String negative, Float rate, UserDTO user, Product product) {
        this.id = id;
        this.positive = positive;
        this.negative = negative;
        this.rate = rate;
        this.user = user;
        this.product = new ProductDTO(product);
    }

    public ReviewDTO() {}
}
