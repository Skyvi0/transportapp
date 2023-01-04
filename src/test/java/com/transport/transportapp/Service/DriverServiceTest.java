package com.transport.transportapp.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transport.transportapp.entity.Driver;
import com.transport.transportapp.repository.DriverRepository;
import com.transport.transportapp.service.DriverService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {
    // This test case verifies that the createDriver, getDriverById, updateDriver,
    // and deleteDriver methods of the DriverService class
    // are working as expected. It does this by using Mockito to mock the
    // DriverRepository and setting up certain return values or behaviors
    // for the repository methods that are called by the service methods. Then, it
    // calls the service methods and verifies that they return the
    // expected values or perform the expected actions.
    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    void testCreateDriver() {
        Driver driver = new Driver();
        driver.setName("John Smith");
        driver.setAddress("123 Main Street");
        driver.setLicenseNumber("123-456-789");

        when(driverRepository.save(driver)).thenReturn(driver);

        Driver createdDriver = driverService.createDriver(driver);
        assertEquals("John Smith", createdDriver.getName());
        assertEquals("123 Main Street", createdDriver.getAddress());
        assertEquals("123-456-789", createdDriver.getLicenseNumber());
    }

    @Test
    void testGetDriverById() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setName("John Smith");
        driver.setAddress("123 Main Street");
        driver.setLicenseNumber("123-456-789");

        when(driverRepository.findById(1L)).thenReturn(java.util.Optional.of(driver));

        Driver retrievedDriver = driverService.getDriverById(1L);
        assertEquals(1L, retrievedDriver.getId());
        assertEquals("John Smith", retrievedDriver.getName());
        assertEquals("123 Main Street", retrievedDriver.getAddress());
        assertEquals("123-456-789", retrievedDriver.getLicenseNumber());
    }

    @Test
    void testUpdateDriver() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setName("John Smith");
        driver.setAddress("123 Main Street");
        driver.setLicenseNumber("123-456-789");

        when(driverRepository.save(driver)).thenReturn(driver);

        Driver updatedDriver = driverService.updateDriver(driver);
        assertEquals(1L, updatedDriver.getId());
        assertEquals("John Smith", updatedDriver.getName());
        assertEquals("123 Main Street", updatedDriver.getAddress());
        assertEquals("123-456-789", updatedDriver.getLicenseNumber());
    }

    @Test
    void testDeleteDriver() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setName("John Smith");
        driver.setAddress("123 Main Street");
        driver.setLicenseNumber("123-456-789");

        driverService.deleteDriver(1L);
        verify(driverRepository).deleteById(1L);

        // We can't verify that the delete operation was successful, since the method
        // returns void
        // Instead, we can just make sure that the repository's deleteById method was
        // called with the correct ID
    }
}
