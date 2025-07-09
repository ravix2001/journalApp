package com.ravi.journalApp.controller;

import com.ravi.journalApp.cache.AppCache;
import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping("/create-admin/{id}")
    @Operation(summary = "Create new user as admin")
    public ResponseEntity<?> createAdmin(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        userService.saveAdmin(objectId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clear-app-cache")
    @Operation(summary = "Clear app cache")
    public void clearAppCache(){
        appCache.init();
    }

    @DeleteMapping("/delete-user/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);

        userService.deleteById(objectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
