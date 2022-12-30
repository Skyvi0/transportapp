package com.transport.transportapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.jdbc.core.JdbcTemplate;

import com.transport.transportapp.entity.User;
import com.transport.transportapp.service.UserService;
import java.sql.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private User testUser;

    private static JdbcTemplate jdbcTemplate;

    /*
     * @BeforeAll
     * public static void createTablesAndView() {
     * Connection connection = null;
     * Statement statement = null;
     * try {
     * // Establish a connection to the database
     * connection =
     * DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1",
     * "testuser",
     * "testpassword");
     * statement = connection.createStatement();
     * 
     * // Create the tables
     * String createUsersTableSql =
     * "CREATE TABLE users (id NUMBER(10) PRIMARY KEY, username VARCHAR2(255), password VARCHAR2(255), role VARCHAR2(255))"
     * ;
     * statement.executeUpdate(createUsersTableSql);
     * 
     * String createRolesTableSql =
     * "CREATE TABLE roles (id NUMBER(10) PRIMARY KEY, name VARCHAR2(255))";
     * statement.executeUpdate(createRolesTableSql);
     * 
     * // Create the view
     * String createUsersViewSql =
     * "CREATE VIEW user_view AS SELECT u.id, u.username, u.role, r.name FROM users u INNER JOIN roles r ON u.role = r.id"
     * ;
     * statement.executeUpdate(createUsersViewSql);
     * } catch (SQLException e) {
     * e.printStackTrace();
     * } finally {
     * // Close the connection and statement
     * try {
     * if (statement != null) {
     * statement.close();
     * }
     * if (connection != null) {
     * connection.close();
     * }
     * } catch (SQLException ex) {
     * ex.printStackTrace();
     * }
     * }
     * }
     */

    /*
     * @AfterAll
     * public static void deleteTablesAndView() {
     * try {
     * // Delete table MY_TABLE
     * jdbcTemplate.execute("DROP TABLE MY_TABLE");
     * // Delete view MY_VIEW
     * jdbcTemplate.execute("DROP VIEW MY_VIEW");
     * } catch (Exception e) {
     * // Log the error and handle it appropriately
     * }
     * }
     */

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
        userService.deleteUser(testUser.getId());
    }

    @Test
    public void testAuthentication() throws Exception {
        // Create a request to the authentication API
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"testpassword\"}"))
                // Check if a 200 OK status code is returned
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenticationFailsWithWrongPassword() throws Exception {
        // Create a request to the authentication API with a wrong password
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testuser\",\"password\":\"wrongpassword\"}"))
                // Check if a 401 Unauthorized status code is returned
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAuthenticationFailsWithWrongUsername() throws Exception {
        // Create a request to the authentication API with a wrong username
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"wrongusername\",\"password\":\"testpassword\"}"))
                // Check if a 401 Unauthorized status code is returned
                .andExpect(status().isUnauthorized());
    }
}