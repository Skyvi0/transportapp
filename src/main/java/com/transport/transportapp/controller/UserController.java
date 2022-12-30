package com.transport.transportapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transport.transportapp.entity.UserSignupRequest;
import com.transport.transportapp.repository.UserRepository;
import com.transport.transportapp.entity.Role;
import com.transport.transportapp.entity.User;
import com.transport.transportapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    String username = "testUser";
    String password = "testPassword";

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }

    @Autowired
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(user);
    }
}
