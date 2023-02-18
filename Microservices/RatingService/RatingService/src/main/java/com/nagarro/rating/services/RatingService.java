package com.nagarro.rating.services;

import com.nagarro.rating.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all by userId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);

}
