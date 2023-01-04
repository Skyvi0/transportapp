package com.transport.transportapp.entity;

import java.io.ObjectStreamClass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = ObjectStreamClass.lookup(Role.class).getSerialVersionUID();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Role() {
        // Der no-arg Konstruktor wird für das serialisieren benötigt, damit das Objekt
        // wiederhergestellt werden kann.
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
