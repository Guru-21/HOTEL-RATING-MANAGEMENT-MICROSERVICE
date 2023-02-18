package com.nagarro.userservice.external.service;


import com.nagarro.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

    //get

    //post
    @PostMapping("/ratings")
    ResponseEntity<Rating> createRating(Rating values);

    //put
    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId);

    //delete
    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);

}
