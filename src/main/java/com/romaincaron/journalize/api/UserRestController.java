package com.romaincaron.journalize.api;

import com.romaincaron.journalize.model.User;
import com.romaincaron.journalize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> users = userService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user)
    {
        User newUser = userService.persist(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user)
    {
        User updatedUser = userService.update(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        userService.delete(id);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
