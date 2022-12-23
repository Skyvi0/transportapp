package com.transport.transportapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private String model;
    private Integer year;
    @ManyToOne
    private TransportCompany transportCompany;

    public Vehicle(String string, String string2, int i, TransportCompany transportCompany2) {
    }
    // Getter- und Setter-Methoden für die Felder
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public TransportCompany getTransportCompany() {
        return transportCompany;
    }
    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
}
