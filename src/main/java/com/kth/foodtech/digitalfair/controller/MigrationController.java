package com.kth.foodtech.digitalfair.controller;


import com.kth.foodtech.digitalfair.model.User;
import com.kth.foodtech.digitalfair.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("migrate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MigrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/set_empty")
    public void setListOfCodes(){
        List<User> users =
                StreamSupport.stream(userRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());

       for(User user: users){
           user.setListOfCodes("");
           userRepository.save(user);
       }
    }
}
