package com.ravi.journalApp.service;
import com.ravi.journalApp.entity.User;
import com.ravi.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;
//    we are playing outside the application context so we do not use @Autowire rather we use @Mock
//    so userRepository is not instantiated automatically
//    therefore we need to initialize manually

//    application context takes long time to load and perform test so we use Mockito instead of JUnit
//    we make fake repository for testing

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadUserByUsernameTest() {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("ravi").password("suyrgeufbsdjhcd").journalEntries(new ArrayList<>()).roles(new ArrayList<>()).build());
        Assertions.assertNotNull(userDetailsServiceImpl.loadUserByUsername("ravi"));

    }
}
