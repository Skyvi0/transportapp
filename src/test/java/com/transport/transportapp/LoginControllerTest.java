package com.transport.transportapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.transport.transportapp.controller.LoginController;
import com.transport.transportapp.entity.LoginRequest;
import com.transport.transportapp.entity.User;
import com.transport.transportapp.service.UserService;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulLogin() {
        // Set up mock user service to return a valid user
        when(userService.findByUsernameAndPassword("user", "password")).thenReturn(new User());

        // Send login request to controller
        LoginRequest loginRequest = new LoginRequest("user", "password");
        ResponseEntity<String> response = loginController.login(loginRequest);

        // Verify that the response is successful
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    void testFailedLogin() {
        // Set up mock user service to return null (no user found)
        when(userService.findByUsernameAndPassword("user", "password")).thenReturn(null);

        // Send login request to controller
        LoginRequest loginRequest = new LoginRequest("user", "password");
        ResponseEntity<String> response = loginController.login(loginRequest);

        // Verify that the response is a failure
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid username or password.", response.getBody());
    }
}
