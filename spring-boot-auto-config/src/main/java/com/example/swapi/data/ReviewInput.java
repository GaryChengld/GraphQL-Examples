package com.example.swapi.data;

import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
public class ReviewInput {
    private Integer stars;
    private String commentary;

    public Review toReview() {
        return new Review(stars, commentary);
    }
}
