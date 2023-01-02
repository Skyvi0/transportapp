package com.transport.transportapp.controller;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {
        return userService.login(loginUser.getUsername(), loginUser.getPassword());
    }

    @GetMapping("/existsByUsername/{username}")
    public boolean existsByUsername(@PathVariable String username) {
        return userService.existsByUsername(username);
    }

    @GetMapping("/findByUsernameAndPassword/{username}/{password}")
    public User findByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        return userService.findByUsernameAndPassword(username, password);
    }

    @GetMapping("/findByUsername/{username}")
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}