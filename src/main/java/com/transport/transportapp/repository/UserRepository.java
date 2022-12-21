package com.transport.transportapp.repository;

import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<User> extends JpaRepository<User, Long> {

    Optional<com.transport.transportapp.model.User> findByUsername(String username);
    
}