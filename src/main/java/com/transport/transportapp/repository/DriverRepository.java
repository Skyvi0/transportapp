package com.transport.transportapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport.transportapp.entity.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    
}
