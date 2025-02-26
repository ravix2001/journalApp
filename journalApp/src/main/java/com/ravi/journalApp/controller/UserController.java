package com.ravi.journalApp.controller;

import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    //controller ---> service ---> repository

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable ObjectId id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> update(@PathVariable String username, @RequestBody User user) {
        User userInDb = userService.findByUsername(username);
        if(userInDb != null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
