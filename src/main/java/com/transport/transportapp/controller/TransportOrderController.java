package com.transport.transportapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transport.transportapp.entity.TransportOrder;
import com.transport.transportapp.service.TransportOrderService;

@RestController
@RequestMapping("/transportorders")
public class TransportOrderController {
    @Autowired
    private TransportOrderService transportOrderService;

    @PostMapping
    public TransportOrder createTransportOrder(@RequestBody TransportOrder transportOrder) {
        return transportOrderService.createTransportOrder(transportOrder);
    }

    @GetMapping("/{id}")
    public TransportOrder getTransportOrderById(@PathVariable Long id) {
        return transportOrderService.getTransportOrderById(id);
    }

    @PutMapping("/{id}")
    public TransportOrder updateTransportOrder(@PathVariable Long id, @RequestBody TransportOrder transportOrder) {
        return transportOrderService.updateTransportOrder(transportOrder);
    }
}
