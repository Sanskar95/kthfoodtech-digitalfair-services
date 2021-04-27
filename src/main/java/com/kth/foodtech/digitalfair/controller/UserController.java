package com.kth.foodtech.digitalfair.controller;


import com.kth.foodtech.digitalfair.exeption.UserAlreadyExistsException;
import com.kth.foodtech.digitalfair.exeption.UserNotFoundException;
import com.kth.foodtech.digitalfair.model.User;
import com.kth.foodtech.digitalfair.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<User>  getUserByUsername (@PathVariable String username) throws UserNotFoundException {
        try{
            User user = userService.getUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (UserNotFoundException unf){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            User newUser=userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/change_points/{username}/{points}")
    public ResponseEntity<User> changeUserPoints(@PathVariable String username, @PathVariable Integer points) {
       try {
           User user=userService.changePoints(username, points);
           return new ResponseEntity<>(user, HttpStatus.OK);
       }
        catch (UserNotFoundException unf){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}