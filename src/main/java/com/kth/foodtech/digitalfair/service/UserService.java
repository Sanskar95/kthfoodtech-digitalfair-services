package com.kth.foodtech.digitalfair.service;


import com.kth.foodtech.digitalfair.exeption.UserAlreadyExistsException;
import com.kth.foodtech.digitalfair.exeption.UserNotFoundException;
import com.kth.foodtech.digitalfair.model.User;
import com.kth.foodtech.digitalfair.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UserNotFoundException("The user does not exist"));
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        try{
            User newUser=userRepository.save(user);
            return newUser;
        }catch (Exception e){
            throw new UserAlreadyExistsException("User already exists");
        }

    }

    public User changePoints(String username, Integer points) throws UserNotFoundException {
        User user = getUserByUsername(username);
        user.setPoints(user.getPoints()+points);
        return userRepository.save(user);
    }

    public User changeSnakePoints(String username, Integer points) throws UserNotFoundException {
        User user = getUserByUsername(username);
        user.setPoints(points);
        return userRepository.save(user);
    }

}
