package com.romaincaron.journalize.repository;

import com.romaincaron.journalize.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Query("SELECT DISTINCT u FROM user u LEFT JOIN FETCH u.roles WHERE u.username = :username")
    public User findByUsername(String username);
}