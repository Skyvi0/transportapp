package com.transport.transportapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.transportapp.entity.TransportOrder;
import com.transport.transportapp.repository.TransportOrderRepository;

@Service
public class TransportOrderService {
    @Autowired
    private TransportOrderRepository transportOrderRepository;

    public TransportOrder createTransportOrder(TransportOrder transportOrder) {
        return transportOrderRepository.save(transportOrder);
    }

    public TransportOrder getTransportOrderById(Long id) {
        return transportOrderRepository.findById(id).orElse(null);
    }

    public TransportOrder updateTransportOrder(TransportOrder transportOrder) {
        return transportOrderRepository.save(transportOrder);
    }

    public void deleteTransportOrder(Long id) {
        transportOrderRepository.deleteById(id);
    }
}