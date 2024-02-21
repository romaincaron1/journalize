package com.romaincaron.journalize.service;

import com.romaincaron.journalize.model.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User persist(User article);
    public User getUser(Long id);
    public User update(User article);
    public void delete(Long id);
}
