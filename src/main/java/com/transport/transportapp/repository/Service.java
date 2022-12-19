package com.transport.transportapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.transport.transportapp.model.Inventory;


public class Service {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory(String name) {
        List<Inventory> inventory = new ArrayList<Inventory>();

        if (name == null)
            inventoryRepository.findAll().forEach(inventory::add);
        else
            inventoryRepository.findByNameContaining(name).forEach(inventory::add);

        return inventory;
    }

    public Inventory getInventoryById(long id) {
        Optional<Inventory> inventoryData = inventoryRepository.findById(id);

        if (inventoryData.isPresent()) {
            return inventoryData.get();
        } else {
            return null;
        }
    }

    public Inventory createInventory(Inventory inventory) {
        Inventory _inventory = inventoryRepository.save(new Inventory(inventory.getName(), inventory.getDescription(), false));
        return _inventory;
    }
    // NEEDS FIXES! Search for Publish to see all lines that do need a fix. ERROR: Unable to locate Attribute  with the the given name [published] on this ManagedType [com.transport.transportapp.model.Inventory]
   /*  public Inventory updateInventory(Inventory inventory) {
        Optional<Inventory> inventoryData = inventoryRepository.findById(inventory.getId());

        if (inventoryData.isPresent()) {
            Inventory _inventory = inventoryData.get();
            _inventory.setName(inventory.getName());
            _inventory.setDescription(inventory.getDescription());
            _inventory.setPublished(inventory.isPublished());
            return inventoryRepository.save(_inventory);
        } else {
            return null;
        }
    }*/

    public void deleteInventory(long id) {
        inventoryRepository.deleteById(id);
    }

    public void deleteAllInventory() {
        inventoryRepository.deleteAll();
    }

    /*public List<Inventory> findByPublished() {
        return inventoryRepository.findByPublished(true);
    }*/
}

// Compare this snippet from src\main\java\com\transport\transportapp\controller\InventoryController.java:
// package com.transport.transportapp.controller;
// 
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// 
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import com.transport.transportapp.model.Inventory;
// import com.transport.transportapp.repository.InventoryRepository;
// 
// 
// 
// public class InventoryController {
// 
//     @Autowired
//     InventoryRepository inventoryRepository;
// 
//     @GetMapping("/inventory")
//