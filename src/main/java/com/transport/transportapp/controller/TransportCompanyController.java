package com.transport.transportapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transport.transportapp.entity.TransportCompany;
import com.transport.transportapp.service.TransportCompanyService;

@RestController
@RequestMapping("/transportcompanies")
public class TransportCompanyController {
    @Autowired
    private TransportCompanyService transportCompanyService;

    @PostMapping
    public TransportCompany createTransportCompany(@RequestBody TransportCompany transportCompany) {
        return transportCompanyService.createTransportCompany(transportCompany);
    }

    @GetMapping("/{id}")
    public TransportCompany getTransportCompanyById(@PathVariable Long id) {
        return transportCompanyService.getTransportCompanyById(id);
    }

    @PutMapping("/{id}")
    public TransportCompany updateTransportCompany(@PathVariable Long id, @RequestBody TransportCompany transportCompany) {
        return transportCompanyService.updateTransportCompany(transportCompany);
    }
}
