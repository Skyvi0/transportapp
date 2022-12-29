package com.transport.transportapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
public class H2ConnectionTest {

    @Test
    public void testH2Connection() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:file:./vitaliy;DB_CLOSE_DELAY=-1", "sa", "")) {
            assertTrue(conn.isValid(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
