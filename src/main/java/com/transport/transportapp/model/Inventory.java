package com.transport.transportapp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "price")
    private String price;

    public Inventory(String name2, String description2, boolean b) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Object getCategory() {
        return null;
    }

    public Object getImage() {
        return null;
    }

    public Object getSubcategory() {
        return null;
    }

    public void setImage(Object image) {

    }

    public void setCategory(Object category) {
    }

    public void setSubcategory(Object subcategory) {
    }

    public Object isPublished() {
        return null;
    }

   // public void setPublished(Object published) {
   // }

}

