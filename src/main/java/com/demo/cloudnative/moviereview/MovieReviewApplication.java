package com.demo.cloudnative.moviereview;

import com.demo.cloudnative.moviereview.model.MovieReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

@SpringBootApplication
public class MovieReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieReviewApplication.class, args);
	}

}

