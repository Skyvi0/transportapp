package com.transport.transportapp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.transport.transportapp.model.Inventory;
import com.transport.transportapp.repository.InventoryService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JunitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JunitTest exampleService;

    @Test
    public void test() throws Exception {
        InventoryService inventoryService = mock(InventoryService.class);
when(inventoryService.getInventoryById(1L)).thenReturn(new Inventory(1L, "Test Inventory", 10));

    }
}
