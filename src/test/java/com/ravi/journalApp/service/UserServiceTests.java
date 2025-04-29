package com.ravi.journalApp.service;

import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetAll(){
        assertNotNull(userService.getAll());
        assertFalse(userService.getAll().isEmpty());
    }

    @Test
    public void testFindByUsername(){
        assertNotNull(userService.findByUsername("ravi"));
    }

    @ParameterizedTest
//    @CsvSource({
//            "ravi",
//            "ram",
//    })
//    we can use @CsvSource or @ValueSource or EnumSource
    @ValueSource(strings ={
            "ravi",
            "ram"
    })
    public void testFindByUsername(String username) {
        assertNotNull(userService.findByUsername(username));
//        assertNotNull(userService.findByUsername(username),"failed for username: " + username);
    }

//    This test failed check this
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }
}
