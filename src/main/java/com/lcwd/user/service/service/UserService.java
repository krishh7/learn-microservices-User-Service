package com.lcwd.user.service.service;

import com.lcwd.user.service.entity.User;

import java.util.List;

public interface UserService {

    //Create
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get single user of a given userId
    User getUser(String userId);

}
