package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {
    //user Operations
    //create
    User saveUser(User user);
    //getAll Users
    List<User> getAllUser();

    //get specificed UserID
    User getUser(String userId);
    
    // TODO: 21/03/23  delete
    void deleteUser(String userId);
    // TODO: 21/03/23 update
    void updateUser(String userId,User user);
}
