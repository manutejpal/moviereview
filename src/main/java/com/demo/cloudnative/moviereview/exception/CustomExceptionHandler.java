package com.demo.cloudnative.moviereview.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends
        ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions (Exception ex, WebRequest request){
        return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RestClientException.class)
    public final ResponseEntity<Object> handleRestClientExceptions (Exception ex, WebRequest request){
        return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MovieReviewNotFoundException.class)
    public final ResponseEntity<Object> handleMovieReviewNotFoundException(MovieReviewNotFoundException ex, WebRequest request){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

}
