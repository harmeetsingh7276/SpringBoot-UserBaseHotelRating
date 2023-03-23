package com.lcwd.user.service;

import com.lcwd.user.service.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Service
@SpringBootTest
class UserServiceApplicationTests {
    @Autowired
    private RatingService ratingService;

    @Test
    void contextLoads() {
    }

    //    @Test
//    void createRating() {
//        Rating rating = Rating.builder().rating(10).userId("2fb51a39-9f61-4cfa-a960-132bd1ab1998").hotelId("99b192b8-4cf5-4b9d-b920-e526505a9526").feedback("Created using feignClient").build();
//        ResponseEntity<Rating> ratingResponse=ratingService.createRating(rating);
//		System.out.println(ratingResponse.getStatusCode()+ratingResponse.getBody().toString());
//    }

//    @Test
//    void updateRating() {
//        Rating rating = Rating.builder().rating(3).feedback("updated using feignClient").build();
//        ResponseEntity<Rating> ratingResponse = ratingService.updateRating("641c1e56d796a44f3642ba57",rating);
//        System.out.println("STATUS CODE=>"+ratingResponse.getStatusCode() + "BODY=>"+ratingResponse.getBody());
//    }

//    @Test
//    void deleteRating() {
//       ratingService.deleteRating("641c1e56d796a44f3642ba57");
//        System.out.println("STATUS CODE=>" + HttpStatus.OK + "BODY=>" + "record Deleted");
//    }
}
