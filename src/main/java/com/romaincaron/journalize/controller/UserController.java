package com.romaincaron.journalize.controller;

import com.romaincaron.journalize.model.User;
import com.romaincaron.journalize.service.UserSecurityService;
import com.romaincaron.journalize.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    UserSecurityService userSecurityService;
    UserService userService;

    public UserController(UserSecurityService userSecurityService, UserService userService) {
        this.userSecurityService = userSecurityService;
        this.userService = userService;
    }
}
