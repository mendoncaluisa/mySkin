package com.mySkin.entities;

import com.mySkin.dtos.ReviewDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String positive;

    @Column (nullable = false)
    private String negative;

    @Column (nullable = false)
    private Float rate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {}

    public Review(ReviewDTO reviewDTO) {
        this.id = reviewDTO.getId();
        this.positive = reviewDTO.getPositive();
        this.negative = reviewDTO.getNegative();
        this.rate = reviewDTO.getRate();
        this.user = new User();
    }

    public Review(Long id, String positive, String negative, Float rate, User user, Product product) {
        this.id = id;
        this.positive = positive;
        this.negative = negative;
        this.rate = rate;
        this.user = user;
        this.product = product;
    }
}
