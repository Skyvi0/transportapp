package com.transport.transportapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.transportapp.entity.Driver;
import com.transport.transportapp.repository.DriverRepository;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public Driver updateDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
