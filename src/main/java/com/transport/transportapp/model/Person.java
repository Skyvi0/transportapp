package com.transport.transportapp.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Person {
    private String name;

    public String getName() {
        return this.name;
    }
}
