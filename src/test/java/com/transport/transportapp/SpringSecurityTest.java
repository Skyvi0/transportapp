package com.transport.transportapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setup() {
        // Erstellen Sie einen Benutzer, der in der Datenbank gespeichert wird
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("testpassword");
        userService.createUser(testUser);
    }

    @AfterEach
    public void cleanup() {
        // Löschen Sie den Benutzer, der während der Tests erstellt wurde
        userService.deleteUser(testUser.getId());
    }

    @Test
    public void testAuthentication() throws Exception {
        // Erstellen Sie eine Anforderung an die Authentifizierungs-API
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"testpassword\"}"))
                // Überprüfen Sie, ob ein 200-OK-Statuscode zurückgegeben wird
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenticationFailsWithWrongPassword() throws Exception {
        // Erstellen Sie eine Anforderung an die Authentifizierungs-API mit einem
        // falschen Passwort
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"wrongpassword\"}"))
                //
                .andExpect(status().isBadRequest());
    }
}