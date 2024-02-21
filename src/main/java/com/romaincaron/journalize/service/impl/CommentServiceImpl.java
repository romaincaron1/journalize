package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.Comment;
import com.romaincaron.journalize.repository.CommentRepository;
import com.romaincaron.journalize.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getComments() {
        return this.commentRepository.findAll();
    }

    @Override
    public Comment persist(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return this.commentRepository.findById(id).orElseThrow();
    }

    @Override
    public Comment update(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.findById(id).orElseThrow();
        commentRepository.deleteById(id);
    }
}
