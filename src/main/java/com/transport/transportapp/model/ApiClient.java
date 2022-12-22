package com.transport.transportapp.model;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiClient {
  private final String apiUrl;
  private final RestTemplate restTemplate;
  private final HttpHeaders headers;

  public ApiClient(String apiUrl) {
    this.apiUrl = apiUrl;
    this.restTemplate = new RestTemplate();
    this.headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  public User createUser(User user) throws IOException {
    HttpEntity<User> request = new HttpEntity<>(user, headers);
    ResponseEntity<User> response = restTemplate.exchange(
        apiUrl + "/users", HttpMethod.POST, request, User.class);
    return response.getBody();
  }

  public User getUser(String id) throws IOException {
    HttpEntity<String> request = new HttpEntity<>(headers);
    ResponseEntity<User> response = restTemplate.exchange(
        apiUrl + "/users/" + id, HttpMethod.GET, request, User.class);
    return response.getBody();
  }

  public User updateUser(User user) throws IOException {
    HttpEntity<User> request = new HttpEntity<>(user, headers);
    ResponseEntity<User> response = restTemplate.exchange(
        apiUrl + "/users/" + user.getId(), HttpMethod.PUT, request, User.class);
    return response.getBody();
  }

  public void deleteUser(String id) throws IOException {
    HttpEntity<String> request = new HttpEntity<>(headers);
    restTemplate.exchange(
        apiUrl + "/users/" + id, HttpMethod.DELETE, request, Void.class);
  }

  public List<User> getAllUsers() throws IOException {
    HttpEntity<String> request = new HttpEntity<>(headers);
    ResponseEntity<User[]> response = restTemplate.exchange(
        apiUrl + "/users", HttpMethod.GET, request, User[].class);
    return Arrays.asList(response.getBody());
  }
}
