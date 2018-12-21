package com.demo.cloudnative.moviereview;

import com.demo.cloudnative.moviereview.exception.MovieReviewNotFoundException;
import com.demo.cloudnative.moviereview.model.MovieReview;
import com.demo.cloudnative.moviereview.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieReviewService implements MovieReviewInterface{
    @Autowired
    private RestTemplate restTemplate;

    @Value( "${review.uri}" )
    private String review_uri;

    @Override
    public MovieReview getReview(String apiKey, String movieName) throws RestClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<MovieReview> response = restTemplate.exchange(
               review_uri+"?"+SearchEnum.API_KEY.getSearchType()+"="+apiKey+"&"+
                       SearchEnum.MOVIE_TITLE.getSearchType()+"="+movieName,
                        HttpMethod.GET, entity, MovieReview.class);
        return response.getBody();
    }

    @Override
    public Rating getRating(String apiKey, String movieName, String ratingSource)
            throws RestClientException,MovieReviewNotFoundException {
        MovieReview movieReview = restTemplate.getForObject(
                review_uri+"?"+SearchEnum.API_KEY.getSearchType()+"="+apiKey+"&"+
                        SearchEnum.MOVIE_TITLE.getSearchType()+"="+movieName, MovieReview.class);
        Rating tomatoRating = null;
        if((movieReview != null) && (!"False".equalsIgnoreCase(movieReview.getResponse()))) {
            tomatoRating = movieReview.getRatings().stream().filter(x ->
                    SearchEnum.SOURCE_ROTTEN_TOMATO.getSearchType().
                    equalsIgnoreCase(x.getSource())).findAny().orElse(null);
            if(tomatoRating == null)
                throw new MovieReviewNotFoundException("Rating not found for movie "+movieName);
        } else {
            tomatoRating = new Rating();
            tomatoRating.setValue("False");
            tomatoRating.setAdditionalProperty("Error","Movie not found!");
        }

        return tomatoRating;
    }
}
