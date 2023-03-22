package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get data from user table
        User user = userRepository.findById(userId).orElseThrow(() -> new ResolutionException("User not found on server with id:" + userId));
        //populating ratings var
        //fetching ratings from ratings table for that user id

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
}

