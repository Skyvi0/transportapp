package com.transport.transportapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String licenseNumber;
    @ManyToOne
    private TransportCompany transportCompany;

    // Getter- und Setter-Methoden für die Felder
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public TransportCompany getTransportCompany() {
        return transportCompany;
    }
    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
}
