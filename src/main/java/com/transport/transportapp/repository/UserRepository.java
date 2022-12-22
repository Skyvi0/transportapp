package com.transport.transportapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.transport.transportapp.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}