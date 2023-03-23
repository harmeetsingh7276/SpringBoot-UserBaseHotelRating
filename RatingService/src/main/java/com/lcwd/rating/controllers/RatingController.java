package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create ratings
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //getAll
    @GetMapping
    public ResponseEntity<List<Rating>> getAll() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    //getRatingByUserId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingsByHotelId(hotelId));
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<?> deleteRatingById(@PathVariable String ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.ok("Rating Deleted with id" + ratingId);
    }
    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRatingById(@PathVariable String ratingId,@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.updateRating(ratingId,rating));
    }
}
