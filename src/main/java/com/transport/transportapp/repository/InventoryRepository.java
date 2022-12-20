package com.transport.transportapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.transport.transportapp.model.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}

