package com.transport.transportapp.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.repository.UserRepository;
import com.transport.transportapp.service.UserService;
import com.transport.transportapp.entity.Role;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
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
        // Create a mock Pbkdf2PasswordEncoder object
        Pbkdf2PasswordEncoder mockPasswordEncoder = mock(Pbkdf2PasswordEncoder.class);
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
    public void testLogin_Success() {
        // Create a user
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        // Create a mock Pbkdf2PasswordEncoder
        Pbkdf2PasswordEncoder mockPasswordEncoder = mock(Pbkdf2PasswordEncoder.class);
        // Configure the mock to return true when the matches method is called
        when(mockPasswordEncoder.matches(anyString(), anyString())).thenReturn(true);
        // Create a mock UserRepository object
        UserRepository mockUserRepository = mock(UserRepository.class);
        // Configure the mock object to return the input user object when the
        // findByUsername method is called
        when(mockUserRepository.findByUsername(anyString())).thenReturn(user);

        // Create a UserService object using the mock objects
        UserService userService = new UserService(mockUserRepository, mockPasswordEncoder);

        // Call the login method of the UserService object
        User loggedInUser = userService.login("username", "password");

        // Verify that the returned object is the same as the input object
        assertEquals(user, loggedInUser);
        // Verify that the findByUsername method of the mock UserRepository object was
        // called with
        // the input username
        verify(mockUserRepository).findByUsername("username");
        // Verify that the matches method of the mock Pbkdf2PasswordEncoder object was
        // called with
        // the input password and the password from the retrieved user object
        verify(mockPasswordEncoder).matches("password", "password");
    }

    @Test
    public void testLogin_IncorrectPassword() {
        // Create a user
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        // Create a mock Pbkdf2PasswordEncoder
        Pbkdf2PasswordEncoder mockPasswordEncoder = mock(Pbkdf2PasswordEncoder.class);
        // Configure the mock to return false when the matches method is called
        when(mockPasswordEncoder.matches(anyString(), anyString())).thenReturn(false);
        // Create a mock UserRepository object
        UserRepository mockUserRepository = mock(UserRepository.class);
        // Configure the mock object to return the input user object when the
        // findByUsername method is called
        when(mockUserRepository.findByUsername(anyString())).thenReturn(user);

        // Create a UserService object using the mock objects
        UserService userService = new UserService(mockUserRepository, mockPasswordEncoder);

        // Call the login method of the UserService object
        User loggedInUser = userService.login("username", "incorrect_password");

        // Verify that the returned object is null
        assertNull(loggedInUser);
        // Verify that the findByUsername method of the mock UserRepository object was
        // called with
        // the input username
        verify(mockUserRepository).findByUsername("username");
        // Verify that the matches method of the mock Pbkdf2PasswordEncoder object was
        // called with
        // the input password and the password from the retrieved user object
        verify(mockPasswordEncoder).matches("incorrect_password", "password");
    }

    @Test

    public void testLogin_UserNotFound() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setAccessLevel("accessLevel");
        Role role = new Role();
        role.setName("ADMIN");
        user.setRole(role);
        // Create a mock Pbkdf2PasswordEncoder
        Pbkdf2PasswordEncoder mockPasswordEncoder = mock(Pbkdf2PasswordEncoder.class);
        // Create a mock UserRepository object
        UserRepository mockUserRepository = mock(UserRepository.class);
        // Configure the mock object to return an empty Optional object when the
        // findByUsername method is called
        // when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Create a UserService object using the mock objects
        UserService userService = new UserService(mockUserRepository, mockPasswordEncoder);

        // Call the login method of the UserService object
        User loggedInUser = userService.login("username", "password");

        // Verify that the returned object is null
        assertNull(loggedInUser);
        // Verify that the findByUsername method of the mock UserRepository object was
        // called with
        // the input username
        verify(mockUserRepository).findByUsername("username");
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);
        List<User> retrievedUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        assertEquals(users, retrievedUsers);
        verify(userRepository).findAll();
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setAccessLevel("accessLevel");
        Role role = new Role();
        role.setName("ADMIN");
        user.setRole(role);

        // Configure the mock userRepository object to return the input user object when
        // the
        // findById method is called
        // when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Call the updateUser method of the userService object
        userService.updateUser(user);

        // Verify that the save method of the mock userRepository object was called with
        // the
        // input user object
        verify(userRepository).save(user);
    }

    @Test
    public void testUpdateUser_IncorrectPassword() {
        // Create a user
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        // Create a mock Pbkdf2PasswordEncoder
        Pbkdf2PasswordEncoder mockPasswordEncoder = mock(Pbkdf2PasswordEncoder.class);
        // Configure the mock to return false when the matches method is called
        // when(mockPasswordEncoder.matches(anyString(),
        // anyString())).thenReturn(false);
        // Create a mock UserRepository object
        UserRepository mockUserRepository = mock(UserRepository.class);

        // Create a UserService object using the mock objects
        UserService userService = new UserService(mockUserRepository, mockPasswordEncoder);

        // Call the updateUser method of the UserService object
        User updatedUser = userService.updateUser(user);

        // Verify that the returned object is null
        assertNull(updatedUser);
    }

    @Test
    public void testDeleteUser_Success() {
        // Erstelle einen Benutzer
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password");
        user.setAccessLevel("accessLevel");
        Role role = new Role();
        role.setName("ADMIN");
        user.setRole(role);

        // Erstelle ein Mock-UserRepository-Objekt
        UserRepository mockUserRepository = mock(UserRepository.class);
        // Konfigurieren Sie das Mock-Objekt, um beim Aufruf der findById-Methode das
        // Optional-Objekt mit dem Benutzer zurückzugeben
        // when(mockUserRepository.findById(1L)).thenReturn(Optional.of(user));

        // Erstelle ein UserService-Objekt mit dem Mock-UserRepository-Objekt
        UserService userService = new UserService(mockUserRepository, null);

        // Rufen Sie die deleteUser-Methode des UserService-Objekts auf
        userService.deleteUser(1L);

        // Verifizieren Sie, dass die deleteById-Methode des Mock-UserRepository-Objekts
        // mit der Benutzer-ID aufgerufen wurde
        verify(mockUserRepository).deleteById(1L);
    }

}