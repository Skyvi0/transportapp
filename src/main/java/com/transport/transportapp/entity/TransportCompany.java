package com.transport.transportapp.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "transport_companies")
public class TransportCompany {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String contactDetails;

    public TransportCompany(String string, String string2, String string3) {
    }
    public TransportCompany() {
    }
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
    public String getContactDetails() {
        return contactDetails;
    }
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
