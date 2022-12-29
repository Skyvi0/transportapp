package com.transport.transportapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transport.transportapp.entity.TransportCompany;
import com.transport.transportapp.service.TransportCompanyService;

@ExtendWith(MockitoExtension.class)
public class TransportCompanyServiceTest {

    @Mock
    private TransportCompanyService transportCompanyServiceMock;

    @Test
    public void testCreateTransportCompany() {
        TransportCompany transportCompany = new TransportCompany();
        transportCompany.setName("Acme Transport Co.");
        transportCompany.setAddress("456 Main Street");
        when(transportCompanyServiceMock.createTransportCompany(transportCompany)).thenReturn(transportCompany);
        TransportCompany result = transportCompanyServiceMock.createTransportCompany(transportCompany);
        assertEquals("Acme Transport Co.", result.getName());
        assertEquals("456 Main Street", result.getAddress());
    }

    @Test
    public void testGetTransportCompanyById() {
        TransportCompany transportCompany = new TransportCompany();
        transportCompany.setName("Acme Transport Co.");
        transportCompany.setAddress("456 Main Street");
        when(transportCompanyServiceMock.getTransportCompanyById(1L)).thenReturn(transportCompany);
        TransportCompany result = transportCompanyServiceMock.getTransportCompanyById(1L);
        assertEquals("Acme Transport Co.", result.getName());
        assertEquals("456 Main Street", result.getAddress());
    }

}