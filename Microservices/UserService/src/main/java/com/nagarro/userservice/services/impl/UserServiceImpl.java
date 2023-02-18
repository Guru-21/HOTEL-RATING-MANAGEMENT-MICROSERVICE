package com.nagarro.userservice.services.impl;

import com.nagarro.userservice.entities.Hotel;
import com.nagarro.userservice.entities.Rating;
import com.nagarro.userservice.entities.User;
import com.nagarro.userservice.exceptions.ResourceNotFoundException;
import com.nagarro.userservice.external.service.HotelService;
import com.nagarro.userservice.repositories.UserRepository;
import com.nagarro.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        //unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
        
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUsers = userRepository.findAll();
         allUsers.iterator().forEachRemaining(user -> {
             user.setRatings(getUser(user.getUserId()).getRatings()); // set the rating of the hotel given by user using service communication
         });
        return allUsers;
    }

    @Override
    public User getUser(String userId) {
        //get user from DB with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not present on server : "+ userId));
        //fetch ratings of the above user from RATING SERVICE
        // localhost:8083/ratings/users/0e3ca6b1-5878-4b60-b75e-be1b513e21be

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+ user.getUserId(), Rating[].class );
        logger.info("{} ",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //localhost:8082/hotels/dfca637e-81e7-4b2e-84bb-ef98b62f8d1f
            //Hotel getHotel =  restTemplate.getForObject("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            //logger.info("{}",getHotel);

            Hotel hotel = hotelService.getHotel(rating.getHotelId());


            //set the hotel to rating
            rating.setHotel(hotel);

            //return rating
            return rating;
        }).collect(Collectors.toList());

        //set rating by user
        user.setRatings(ratingList);
        return user;
    }
}
