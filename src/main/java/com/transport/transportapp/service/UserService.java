package com.transport.transportapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Validate the user input, e.g. check for required fields

        // Validate the user input, e.g. check for required fields
        if (user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }
        // Hash the password and set it on the user object
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        // Save the user to the database
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User login(String username, String password) {
        // Find the user with the given username
        User user = userRepository.findByUsername(username);
        // If no user was found, return null
        if (user == null) {
            return null;
        }
        // Check if the given password matches the hashed password of the user
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        // If the password doesn't match, return null
        return null;
    }

    public Object findByUsernameAndPassword(String string, String string2) {
        return null;
    }
}
