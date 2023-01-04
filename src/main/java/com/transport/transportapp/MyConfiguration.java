package com.transport.transportapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.transport.transportapp.security.JwtConfigurer;

@Configuration
public class MyConfiguration {
    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}