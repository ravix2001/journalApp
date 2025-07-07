package com.ravi.journalApp.controller;

import com.ravi.journalApp.dto.UserDTO;
import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.service.UserDetailsServiceImpl;
import com.ravi.journalApp.service.UserService;
import com.ravi.journalApp.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/public")
@Tag(name = "Public API", description = "health-check, login and signup")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

//    using User entity

//    @PostMapping("/signup")
//    public void signup(@RequestBody User user) {
//        userService.saveNewUser(user);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
//            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
//            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
//        }catch (Exception e){
//            log.error("Error while logging in: {}", e.getMessage());
//            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
//        }
//    }

    //    using UserDTO instead of User entity
    @PostMapping("/signup")
    @Operation(summary = "Create/Signup a new user")
    public ResponseEntity<?> signup(@RequestBody UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());        // get username from request body(i.e. UserDTO) and save it in User entity
        newUser.setPassword(user.getPassword());        // lly for these
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());
        newUser.setEmail(user.getEmail());
        userService.saveNewUser(newUser);

        String jwtToken = jwtUtil.generateToken(user.getUsername());
        return new ResponseEntity<>(Collections.singletonMap("token", jwtToken), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try{
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(Collections.singletonMap("token", jwtToken)
                    , HttpStatus.OK);
        }catch (Exception e){
            log.error("Error while logging in: {}", e.getMessage());
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }

}
