package com.transport.transportapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private Pbkdf2PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return (UserDetails) user;
    }

    @Autowired
    public UserService(UserRepository userRepository, Pbkdf2PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
