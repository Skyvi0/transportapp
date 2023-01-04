package com.transport.transportapp.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.transport.transportapp.entity.TransportCompany;
import com.transport.transportapp.entity.Vehicle;
import com.transport.transportapp.repository.VehicleRepository;
import com.transport.transportapp.service.VehicleService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    public void testCreateVehicle() {
        TransportCompany transportCompany = new TransportCompany("Company 1", "Address 1", "contact@company1.com");
        Vehicle vehicle = new Vehicle("Truck", "Model 1", 2020, transportCompany);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        Vehicle retrievedVehicle = vehicleService.createVehicle(vehicle);
        assertEquals(vehicle, retrievedVehicle);
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    public void testGetVehicleById() {
        TransportCompany transportCompany = new TransportCompany("Company 1", "Address 1", "contact@company1.com");
        Vehicle vehicle = new Vehicle("Truck", "Model 1", 2020, transportCompany);
        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        Vehicle retrievedVehicle = vehicleService.getVehicleById(1L);
        assertEquals(vehicle, retrievedVehicle);
        verify(vehicleRepository).findById(1L);
    }

    @Test
    public void testUpdateVehicle() {
        TransportCompany transportCompany = new TransportCompany("Company 1", "Address 1", "contact@company1.com");
        Vehicle vehicle = new Vehicle("Truck", "Model 1", 2020, transportCompany);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
        assertEquals(vehicle, updatedVehicle);
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    public void testDeleteVehicle() {
        vehicleService.deleteVehicle(1L);
        verify(vehicleRepository).deleteById(1L);
    }
}
