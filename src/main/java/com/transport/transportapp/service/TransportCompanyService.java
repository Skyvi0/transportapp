package com.transport.transportapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transport.transportapp.repository.TransportCompanyRepository;
import com.transport.transportapp.entity.TransportCompany;

@Service
public class TransportCompanyService {
    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    public TransportCompany createTransportCompany(TransportCompany transportCompany) {
        return transportCompanyRepository.save(transportCompany);
    }

    public TransportCompany getTransportCompanyById(Long id) {
        return transportCompanyRepository.findById(id).orElse(null);
    }

    public TransportCompany updateTransportCompany(TransportCompany transportCompany) {
        return transportCompanyRepository.save(transportCompany);
    }

    public void deleteTransportCompany(Long id) {
        transportCompanyRepository.deleteById(id);
    }
}