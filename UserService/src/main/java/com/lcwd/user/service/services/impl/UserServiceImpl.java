package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> listOfUsers = userRepository.findAll();
        for (User user : listOfUsers) {
            Rating[] ratingsForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            if (ratingsForUser == null) {
                return listOfUsers;
            }
            List<Rating> ratings = Arrays.stream(ratingsForUser).toList();
            List<Rating> ratingList = getHotelInfo(ratings);
            user.setRating(ratingList);
        }
        return listOfUsers;
    }

    @Override
    public User getUser(String userId) {
        //get data from user table
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on server with id:" + userId));
        //populating ratings var
        //fetching ratings from ratings table for that user id
        //http://localhost:8083/ratings/users/2fb51a39-9f61-4cfa-a960-132bd1ab1998
        Rating[] ratingsForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        if (ratingsForUser == null) {
            return user;
        }
        List<Rating> ratings = Arrays.stream(ratingsForUser).toList();
        logger.info("List:{}", ratingsForUser);
        //get hotel info
        List<Rating> ratingList = getHotelInfo(ratings);
        user.setRating(ratingList);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(String userId, User user) {
        User userData = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setAbout(user.getAbout());
        userRepository.save(userData);
    }

    public List<Rating> getHotelInfo(List<Rating> ratings) {
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //Using restTemplate
            //ResponseEntity<Hotel> hotelData = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            logger.info("Hotel-Service Call for hotel id:{}", hotelData.getBody().getId());
//            logger.info("Hotel-Service Call HTTP STATUS CODE:{}", hotelData.getStatusCode());
//            Hotel hotel = hotelData.getBody();
            //Using FeignClient
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        return ratingList;
    }

}

