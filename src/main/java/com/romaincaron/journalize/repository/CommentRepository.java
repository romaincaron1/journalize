package com.romaincaron.journalize.repository;

import com.romaincaron.journalize.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}