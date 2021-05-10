package com.kth.foodtech.digitalfair.service;


import com.kth.foodtech.digitalfair.exeption.CodeInvalidException;
import com.kth.foodtech.digitalfair.exeption.UserAlreadyExistsException;
import com.kth.foodtech.digitalfair.exeption.UserNotFoundException;
import com.kth.foodtech.digitalfair.model.RedeemCode;
import com.kth.foodtech.digitalfair.model.User;
import com.kth.foodtech.digitalfair.repository.RedeemCodeRepository;
import com.kth.foodtech.digitalfair.repository.UserRepository;
import com.kth.foodtech.digitalfair.request.RedeemCodeApplyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedeemCodeRepository redeemCodeRepository;

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

    public User changePointsAndAddCompany(String username, Integer points, String company) throws UserNotFoundException {
        User user = getUserByUsername(username);
        user.setPoints(user.getPoints()+ points);
        String existingString = user.getListOfCompanies();
        existingString= existingString.concat(company).concat("-");
        user.setListOfCompanies(existingString);
        return userRepository.save(user);
    }

    public User changeSnakePoints(String username, Integer points) throws UserNotFoundException {
        User user = getUserByUsername(username);
        user.setSnakePoints(points);
        user.setSnakePlayed(true);
        return userRepository.save(user);
    }

    public User applyRedeemCode(RedeemCodeApplyRequest redeemCodeApplyRequest) throws UserNotFoundException, CodeInvalidException {
        User user = getUserByUsername(redeemCodeApplyRequest.getUsername());
        Boolean codeValidFlag= false;
        List<RedeemCode> redeemCodes =
                StreamSupport.stream(redeemCodeRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());

        for (RedeemCode redeemCode1 : redeemCodes) {
           if(redeemCode1.getCode().equals(redeemCodeApplyRequest.getRedeemCode()) && !redeemCode1.getUsedOnceFlag()){
               user.setEmail(redeemCodeApplyRequest.getEmail());
               user.setPoints(user.getPoints()+redeemCode1.getPoints());
               redeemCode1.setUsedOnceFlag(true);
               codeValidFlag= true;
               break;
           }
        }
        if(!codeValidFlag){
            throw new CodeInvalidException("Invalid Code");
        }

       return  userRepository.save(user);
    }

}
