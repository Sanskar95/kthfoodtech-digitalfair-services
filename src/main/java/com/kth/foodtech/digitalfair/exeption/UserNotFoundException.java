package com.kth.foodtech.digitalfair.exeption;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}
