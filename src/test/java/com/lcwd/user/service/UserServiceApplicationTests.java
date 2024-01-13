package com.lcwd.user.service;

import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	//For Testing
//	@Test
//	public void createRating() {
//		Rating created_using_feign_client = Rating.builder().rating(5).userId("123").hotelId("456").feedback("Created using Feign Client").build();
//		ratingService.createRating(created_using_feign_client);
//		System.out.println("New Rating created");
//	}

}
