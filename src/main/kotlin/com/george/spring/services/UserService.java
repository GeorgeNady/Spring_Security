package com.george.spring.services;

import com.george.spring.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findUser(Long userId);
    User createUser(User user);
}
