package com.transport.transportapp.controller;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.security.JwtTokenProvider;
import com.transport.transportapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.transport.transportapp.security.AuthenticationResponse;

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
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        // Prüfe, ob der Benutzername und das Passwort korrekt sind
        User user = userService.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if (user == null) {
            return ResponseEntity.badRequest().body("Benutzername oder Passwort ist falsch");
        }
        // Erstelle ein JWT-Token für den Benutzer
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String token = jwtTokenProvider.createToken(user.getUsername());
        // Gib das Token zurück
        return ResponseEntity.ok(new AuthenticationResponse(token));
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