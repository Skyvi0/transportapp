package com.transport.transportapp;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.transport.transportapp.model.ApiClient;
import com.transport.transportapp.model.User;

public class TestAPI {
  private static final String API_URL = "http://localhost:8080/api";
  private static final String RESOURCE_PATH = "/users";


  private ApiClient client;

  
 
  
  @Before
  public void setup() {
    client = new ApiClient(API_URL);
  }

  @Test
  public void testConnection() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
    dataSource.setUsername("test");
    dataSource.setPassword("secret");
  }

  @Test
  public void testCreateReadUpdateDelete() throws IOException {
    // Create a new user
    User user = new User("John", "Doe", "john@example.com");
    User createdUser = client.createUser(user);
    assertNotNull(createdUser.getId());

    // Read the user back
    User readUser = client.getUser(createdUser.getId());
    assertEquals(user.getFirstName(), readUser.getFirstName());
    assertEquals(user.getLastName(), readUser.getLastName());
    assertEquals(user.getEmail(), readUser.getEmail());

    // Update the user
    readUser.setFirstName("Jane");
    User updatedUser = client.updateUser(readUser);
    assertEquals("Jane", updatedUser.getFirstName());

    // Delete the user
    client.deleteUser(updatedUser.getId());
    User deletedUser = client.getUser(updatedUser.getId());
    assertNull(deletedUser);
  }

  @Test
  public void testGetAll() throws IOException {
    // Create two new users
    User user1 = new User("John", "Doe", "john@example.com");
    User createdUser1 = client.createUser(user1);
    User user2 = new User("Jane", "Doe", "jane@example.com");
    User createdUser2 = client.createUser(user2);

    // Get all users
    List<User> users = client.getAllUsers();
    assertEquals(2, users.size());

    // Clean up
    client.deleteUser(createdUser1.getId());
    client.deleteUser(createdUser2.getId());
  }
}