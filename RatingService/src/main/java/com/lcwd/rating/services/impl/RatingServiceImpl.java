package com.lcwd.rating.services.impl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.exception.ResourceNotFoundException;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }
    //Custom Queries
    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        Rating ratingData=ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating not found on server with id:" + ratingId));
        ratingData.setRating(rating.getRating());
        ratingData.setFeedback(rating.getFeedback());
        ratingRepository.save(ratingData);
        return ratingData;
    }

    @Override
    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
