package com.nagarro.userservice.services;

import com.nagarro.userservice.entities.User;

import java.util.List;

public interface UserService {

    //create user
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userId
    User getUser(String userId);



}
