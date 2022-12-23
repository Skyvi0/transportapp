package com.transport.transportapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.transport.transportapp.entity.TransportCompany;
import com.transport.transportapp.service.TransportCompanyService;

@SpringBootTest
public class TransportCompanyServiceTest {
    @Autowired
    private TransportCompanyService transportCompanyService;

    @Test
    public void testCreateTransportCompany() {
        TransportCompany transportCompany = new TransportCompany();
        transportCompany.setName("Acme Transport Co.");
        transportCompany.setAddress("456 Main Street");
        transportCompany = transportCompanyService.createTransportCompany(transportCompany);
        assertEquals("Acme Transport Co.", transportCompany.getName());
        assertEquals("456 Main Street", transportCompany.getAddress());
    }

    @Test
    public void testGetTransportCompanyById() {
        TransportCompany transportCompany = transportCompanyService.getTransportCompanyById(1L);
        assertEquals("Acme Transport Co.", transportCompany.getName());
        assertEquals("456 Main Street", transportCompany.getAddress());
    }

    @Test
    public void testUpdateTransportCompany() {
        TransportCompany transportCompany = transportCompanyService.getTransportCompanyById(1L);
        transportCompany.setAddress("456 Main Street");
        transportCompany = transportCompanyService.updateTransportCompany(transportCompany);
        assertEquals("Acme Transport Co.", transportCompany.getName());
        assertEquals("456 Main Street", transportCompany.getAddress());
    }
}
