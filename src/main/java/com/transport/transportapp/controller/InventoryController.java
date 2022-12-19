package com.transport.transportapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.transport.transportapp.model.Inventory;
import com.transport.transportapp.repository.InventoryRepository;



public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getAllInventory(@RequestParam(required = false) String name) {
        try {
            List<Inventory> inventory = new ArrayList<Inventory>();

            if (name == null)
                inventoryRepository.findAll().forEach(inventory::add);
            else
                inventoryRepository.findByNameContaining(name).forEach(inventory::add);

            if (inventory.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable("id") long id) {
        Optional<Inventory> inventoryData = inventoryRepository.findById(id);

        if (inventoryData.isPresent()) {
            return new ResponseEntity<>(inventoryData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/inventory")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        try {
            Inventory _inventory = inventoryRepository
                    .save(new Inventory(null, null, false));
            return new ResponseEntity<>(_inventory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") long id, @RequestBody Inventory inventory) {
        Optional<Inventory> inventoryData = inventoryRepository.findById(id);

        if (inventoryData.isPresent()) {
            Inventory _inventory = inventoryData.get();
            _inventory.setName(inventory.getName());
            _inventory.setDescription(inventory.getDescription());
            _inventory.setPrice(inventory.getPrice());
            _inventory.setQuantity(inventory.getQuantity());
            _inventory.setImage(inventory.getImage());
            _inventory.setCategory(inventory.getCategory());
            _inventory.setSubcategory(inventory.getSubcategory());
            return new ResponseEntity<>(inventoryRepository.save(_inventory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<HttpStatus> deleteInventory(@PathVariable("id") long id) {
        try {
            inventoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/inventory")
    public ResponseEntity<HttpStatus> deleteAllInventory() {
        try {
            inventoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

   /*  @GetMapping("/inventory/published")
    public ResponseEntity<List<Inventory>> findByPublished() {
        try {
            List<Inventory> inventory = inventoryRepository.findByPublished(true);

            if (inventory.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}