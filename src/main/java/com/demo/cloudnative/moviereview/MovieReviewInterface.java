package com.demo.cloudnative.moviereview;

import com.demo.cloudnative.moviereview.model.MovieReview;
import com.demo.cloudnative.moviereview.model.Rating;

public interface MovieReviewInterface {
    public MovieReview getReview(String apiKey, String movieName);
    public Rating getRating(String apiKey, String movieName, String ratingSource);
}
