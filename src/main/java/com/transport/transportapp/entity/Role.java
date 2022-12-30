package com.transport.transportapp.entity;

import java.io.ObjectStreamClass;
import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = ObjectStreamClass.lookup(Role.class).getSerialVersionUID();

    private String name;

    public Role() {
        // no-arg constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
