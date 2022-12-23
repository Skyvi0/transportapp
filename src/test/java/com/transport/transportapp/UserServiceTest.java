package com.transport.transportapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.repository.UserRepository;
import com.transport.transportapp.service.UserService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
        when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals(user, createdUser);
        verify(userRepository).save(user);
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
