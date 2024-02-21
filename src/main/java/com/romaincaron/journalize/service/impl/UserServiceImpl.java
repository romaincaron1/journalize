package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.User;
import com.romaincaron.journalize.repository.UserRepository;
import com.romaincaron.journalize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User persist(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return this.userRepository.findById(id).orElseThrow();
    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }
}
