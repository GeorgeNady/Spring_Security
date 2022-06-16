package com.george.spring.services;

import com.george.spring.model.User;
import com.george.spring.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo repo;

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public User findUser(Long userId) {
        return repo.findById(userId).orElseThrow(IllegalStateException::new);
    }

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }
}
