package com.transport.transportapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transport.transportapp.model.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //@Query("SELECT i FROM Inventory i WHERE i.name LIKE %?1%")
    List<Inventory> findByNameContaining(String name);

    Inventory save(Inventory inventory);

    //List<Inventory> findByPublished(boolean b);
    
}
