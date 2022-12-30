package com.transport.transportapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.repository.UserRepository;
import com.transport.transportapp.service.UserService;
import com.transport.transportapp.entity.Role;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setAccessLevel("accessLevel");
        Role role = new Role();
        role.setName("ADMIN");
        user.setRole(role);

        // Create a mock PasswordEncoder object
        PasswordEncoder mockPasswordEncoder = mock(PasswordEncoder.class);
        // Configure the mock object to return a predetermined value when the encode
        // method is called
        when(mockPasswordEncoder.encode(anyString())).thenReturn("encoded_password");

        // Create a mock UserRepository object
        UserRepository mockUserRepository = mock(UserRepository.class);
        // Configure the mock object to return the input user object when the save
        // method is called
        when(mockUserRepository.save(user)).thenReturn(user);

        // Create a UserService object using the mock objects
        UserService userService = new UserService(mockUserRepository, mockPasswordEncoder);

        // Call the createUser method of the UserService object
        User createdUser = userService.createUser(user);

        // Verify that the returned object is the same as the input object
        assertEquals(user, createdUser);
        // Verify that the save method of the mock UserRepository object was called with
        // the input user object
        verify(mockUserRepository).save(user);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User retrievedUser = userService.getUserById(1L);
        assertEquals(user, retrievedUser);
        verify(userRepository).findById(1L);
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        User retrievedUser = userService.getUserById(1L);
        assertNull(retrievedUser);
        verify(userRepository).findById(1L);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(user);
        assertEquals(user, updatedUser);
        verify(userRepository).save(user);
    }

    @Test
    public void testGetAllUsers() {
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }
}
