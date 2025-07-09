package com.ravi.journalApp.controller;

import com.ravi.journalApp.api.response.WeatherResponse;
import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.repository.UserRepository;
import com.ravi.journalApp.service.UserService;
import com.ravi.journalApp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User API", description = "read, update and delete")
public class UserController {

    //controller ---> service ---> repository

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    @Operation(summary = "Update user")
    public ResponseEntity<?> update(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userInDb.setEmail(user.getEmail());
            userInDb.setSentimentAnalysis(user.isSentimentAnalysis());
            userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Operation(summary = "Delete user")
    public ResponseEntity<?> deleteByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Greetings")
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Biratnagar");
        String greeting = "";
        if(weatherResponse != null){
            int feelsLike = (int) (weatherResponse.getMain().getFeelsLike() - 273);
            int temp = (int) (weatherResponse.getMain().getTemp() - 273);
            greeting = "! Weather feels like " + feelsLike + " and temperature is " + temp + " degrees Celsius";
        }
        return new ResponseEntity<>("Hi, " + authentication.getName() + greeting, HttpStatus.OK);
    }

}