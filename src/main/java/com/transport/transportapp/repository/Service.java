package com.transport.transportapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.transport.transportapp.model.Inventory;


public class Service {
    @Autowired
    private InventoryRepository inventoryRepository;
    
    public Inventory saveInventory(Inventory inventory) {
        return inventory;

    }
 

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }
}