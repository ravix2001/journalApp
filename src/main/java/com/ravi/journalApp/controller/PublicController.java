package com.ravi.journalApp.controller;

import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/create-user")
    public void create(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
