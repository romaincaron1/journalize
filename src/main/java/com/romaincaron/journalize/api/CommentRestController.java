package com.romaincaron.journalize.api;

import com.romaincaron.journalize.model.Comment;
import com.romaincaron.journalize.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {
    private CommentService commentService;

    @Autowired
    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments()
    {
        List<Comment> comments = commentService.getComments();
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") long id) {
        Comment comment = commentService.getComment(id);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> add(@RequestBody Comment comment)
    {
        Comment newComment = commentService.persist(comment);
        return new ResponseEntity<Comment>(newComment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> update(@RequestBody Comment comment)
    {
        Comment updatedComment = commentService.update(comment);
        return new ResponseEntity<Comment>(updatedComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        commentService.delete(id);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
