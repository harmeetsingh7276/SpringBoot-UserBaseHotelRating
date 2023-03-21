package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);
    //getAll
    List<Rating> getRatings();
    //getByRatingsByUserId
    List<Rating> getRatingsByUserId(String userId);
    //getAllRatingsbyHotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
