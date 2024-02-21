package com.romaincaron.journalize.service;

import com.romaincaron.journalize.model.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getComments();
    public Comment persist(Comment article);
    public Comment getComment(Long id);
    public Comment update(Comment article);
    public void delete(Long id);
}
