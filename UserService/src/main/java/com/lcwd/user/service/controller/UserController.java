package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userData = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userData);
    }

    //single user
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User userData = userService.getUser(userId);
        return ResponseEntity.ok(userData);
    }

    //allUser
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userData = userService.getAllUser();
        return ResponseEntity.ok(userData);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted:" + userId);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.ok(user);
    }
}
