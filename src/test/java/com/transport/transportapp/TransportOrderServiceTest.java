package com.transport.transportapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.transport.transportapp.entity.TransportOrder;
import com.transport.transportapp.repository.TransportOrderRepository;
import com.transport.transportapp.service.TransportOrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class TransportOrderServiceTest {

    @Mock
    private TransportOrderRepository transportOrderRepository;

    @InjectMocks
    private TransportOrderService transportOrderService;

    @Test
    void testCreateTransportOrder() {
        TransportOrder transportOrder = new TransportOrder();
        transportOrder.setSender("Alice");
        transportOrder.setRecipient("Bob");
        transportOrder.setGoods("Books");
        transportOrder.setTransportTime(new Date());

        when(transportOrderRepository.save(transportOrder)).thenReturn(transportOrder);

        TransportOrder createdTransportOrder = transportOrderService.createTransportOrder(transportOrder);
        assertEquals("Alice", createdTransportOrder.getSender());
        assertEquals("Bob", createdTransportOrder.getRecipient());
        assertEquals("Books", createdTransportOrder.getGoods());
        assertEquals(new Date(), createdTransportOrder.getTransportTime());
    }

    @Test
    void testGetTransportOrderById() {
        TransportOrder transportOrder = new TransportOrder();
        Date transportTime = new Date();
        transportOrder.setId(1L);
        transportOrder.setSender("Alice");
        transportOrder.setRecipient("Bob");
        transportOrder.setGoods("Books");
       transportOrder.setTransportTime(transportTime);
        assertEquals(transportTime, transportOrder.getTransportTime());

        when(transportOrderRepository.findById(1L)).thenReturn(java.util.Optional.of(transportOrder));

        TransportOrder retrievedTransportOrder = transportOrderService.getTransportOrderById(1L);
        assertEquals(1L, retrievedTransportOrder.getId());
        assertEquals("Alice", retrievedTransportOrder.getSender());
        assertEquals("Bob", retrievedTransportOrder.getRecipient());
        assertEquals("Books", retrievedTransportOrder.getGoods());
        assertEquals(transportTime, retrievedTransportOrder.getTransportTime());
        //assertEquals(new Date(), retrievedTransportOrder.getTransportTime()); Broken test
    }

    @Test
    void testUpdateTransportOrder() {
        TransportOrder transportOrder = new TransportOrder();
        transportOrder.setId(1L);
        transportOrder.setSender("Alice");
        transportOrder.setRecipient("Bob");
        transportOrder.setGoods("Books");
        transportOrder.setTransportTime(new Date());

        when(transportOrderRepository.save(transportOrder)).thenReturn(transportOrder);

        TransportOrder updatedTransportOrder = transportOrderService.updateTransportOrder(transportOrder);
        assertEquals(1L, updatedTransportOrder.getId());
    }

        @Test
        void testDeleteTransportOrder() {
            transportOrderService.deleteTransportOrder(1L);
            verify(transportOrderRepository).deleteById(1L);
            // We can't verify that the delete operation was successful, since the method returns void
            // Instead, we can just make sure that the repository's deleteById method was called with the correct ID
        }
    }