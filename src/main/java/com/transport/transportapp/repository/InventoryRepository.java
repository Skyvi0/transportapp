package com.transport.transportapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.transportapp.model.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByNameContaining(String name);
}
