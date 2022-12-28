package com.transport.transportapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.transport.transportapp.controller.RegisterController;
import com.transport.transportapp.entity.User;
import com.transport.transportapp.service.UserService;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class RegisterControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private RegisterController registerController;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void CreateUserTest() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.createUser(user)).thenReturn(user);
        ResponseEntity<String> result = registerController.register(user);
        assertEquals("User created successfully.", result.getBody());
    }

}
