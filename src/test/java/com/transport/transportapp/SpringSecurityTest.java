package com.transport.transportapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.repository.UserRepository;
import com.transport.transportapp.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringSecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setup() {
        // Create a user that will be stored in the database
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("testpassword");
        com.transport.transportapp.entity.Role role = new com.transport.transportapp.entity.Role();
        role.setName("USER");
        testUser.setRole(role);
        userService.createUser(testUser);
    }

    @AfterEach
    public void cleanup() {
        // Delete the user that was created during the tests
        userRepository.deleteById(testUser.getId());
    }

    @Test
    public void testAuthentication() throws Exception {
        // Create a request to the authentication API
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"testpassword\"}"))
                // Check if a 200 OK status code is returned
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAuthenticationFailsWithWrongPassword() throws Exception {
        // Create a request to the authentication API with a wrong password
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"wrongpassword\"}"))
                // Check if a 400 Bad Request status code is returned
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAuthenticationFailsWithWrongUsername() throws Exception {
        // Create a request to the authentication API with a wrong username
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"wrongUsername\",\"password\":\"testpassword\"}"))
                // Check if a 400 Bad Request status code is returned
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
