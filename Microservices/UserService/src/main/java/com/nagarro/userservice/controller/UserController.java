package com.nagarro.userservice.controller;


import com.nagarro.userservice.entities.User;
import com.nagarro.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    //create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User userData = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userData);
    }


    //single user
    @GetMapping("/{userId}")
    // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
   // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        //logger.info("Fallback is executed because service is down: ", ex.getMessage());
        //ex.printStackTrace();
        User user = User.builder()
                    .email("dummy@gmail.com")
                    .name("Dummy")
                    .about("The dummy user is created because some service is down")
                    .userId("123458666")
                    .phone("9876543210")
                    .build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }


    //all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUser();
        return ResponseEntity.ok(allUsers);
    }
}
