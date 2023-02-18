package com.nagarro.userservice;

import com.nagarro.userservice.entities.Rating;
import com.nagarro.userservice.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {


	@Autowired
	private RatingService ratingService;
	@Test
	void contextLoads() {
	}

	@Test
	void createRating(){
		Rating rating= Rating.builder().rate(10).userId("0e3ca6b1-5878-4b60-b75e-be1b513e21be").hotelId("882674e3-63b1-4e0a-8865-2614e55eb12d").feedback("This is created using feign client...").build();
		ResponseEntity<Rating> saveRating = ratingService.createRating(rating);
		System.out.println("New Rating being created.....");
	}

}
