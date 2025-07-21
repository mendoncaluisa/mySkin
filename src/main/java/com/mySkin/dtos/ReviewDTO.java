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

    private Long user_id;

    private Long product_id;

    private UserDTO user = new UserDTO();

    private ProductDTO product = new ProductDTO();

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.positive = review.getPositive();
        this.negative = review.getNegative();
        this.rate = review.getRate();
        this.user_id = review.getUser().getId();
        this.product_id = review.getProduct().getId();
        this.user = new UserDTO(review.getUser());
        this.product = new ProductDTO(review.getProduct());
    }

    public ReviewDTO(Long id, String positive, String negative, Float rate, Long user_id, Long product_id) {
        this.id = id;
        this.positive = positive;
        this.negative = negative;
        this.rate = rate;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public ReviewDTO() {}
}
