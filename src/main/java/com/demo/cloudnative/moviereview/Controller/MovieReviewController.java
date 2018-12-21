package com.demo.cloudnative.moviereview.Controller;

import com.demo.cloudnative.moviereview.MovieReviewService;
import com.demo.cloudnative.moviereview.SearchEnum;
import com.demo.cloudnative.moviereview.model.MovieReview;
import com.demo.cloudnative.moviereview.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestController
public class MovieReviewController {

    @Autowired
    MovieReviewService movieReviewService;

    @GetMapping(value="/")
    public String checkStatus() {
        String up = "I am up!!";
            return up;
            }

    @GetMapping(value="/v1/movie",produces="application/json")
    public Resource<MovieReview> getMovieReview(@RequestParam String apiKey,
                                      @RequestParam String movieName){
        MovieReview movieReview = movieReviewService.getReview(apiKey,movieName);
        Resource<MovieReview> movieResource = new Resource<MovieReview>(movieReview);
        if(!"False".equalsIgnoreCase(movieReview.getResponse())) {
            ControllerLinkBuilder toLink = ControllerLinkBuilder.
                    linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getMovieRating(apiKey, movieName));
            movieResource.add(toLink.withRel("movie-rating"));
        }
        return movieResource;
    }

    @GetMapping(value="/v1/movie/rating",produces="application/json")
    public Resource<Rating> getMovieRating(@RequestParam String apiKey,
                                 @RequestParam String movieName){

        Rating movieRating = movieReviewService.getRating(apiKey,movieName,
                SearchEnum.SOURCE_ROTTEN_TOMATO.getSearchType());
        Resource<Rating> ratingResource = new Resource<Rating>(movieRating);

        if((movieRating != null) && (!"False".equalsIgnoreCase(movieRating.getValue()))) {
            ControllerLinkBuilder toLink = ControllerLinkBuilder.
                    linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getMovieReview(apiKey, movieName));
            ratingResource.add(toLink.withRel("movie-review"));
        }
        return ratingResource;
    }
}
