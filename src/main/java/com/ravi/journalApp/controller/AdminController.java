package com.ravi.journalApp.controller;

import com.ravi.journalApp.cache.AppCache;
import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin API", description = "Admin operations")      // for swagger Api
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;


    @GetMapping("/all-users")
    @Operation(summary="Get all users")         // for swagger Api
    public ResponseEntity<?> getAll() {
        List<User> all = userService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/create-admin")
    @Operation(summary = "Create new user as admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clear-app-cache")
    @Operation(summary = "Clear app cache")
    public void clearAppCache(){
        appCache.init();
    }
}
