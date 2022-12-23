package com.transport.transportapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport.transportapp.entity.TransportOrder;

@Repository
public interface TransportOrderRepository extends JpaRepository<TransportOrder, Long> {
    
}