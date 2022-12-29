package com.transport.transportapp;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import oracle.jdbc.dcn.DatabaseChangeRegistration;

import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseConnectionTest {

    // private static final Properties props = new Properties();
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String DB_USER = "testuser";
    private static final String DB_PASSWORD = "testpassword";
    /*
     * static {
     * try {
     * props.load(DatabaseChangeRegistration.class.getResourceAsStream(
     * "/application.properties"));
     * } catch (IOException e) {
     * // Fehlerbehandlung
     * }
     * }
     */ // future use for properties file with database connection details (url, user,
        // password)

    @BeforeAll
    public static void setUp() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String createTableSql = "CREATE TABLE test_table (col1 NUMBER, col2 VARCHAR2(255))";
            String createViewSql = "CREATE VIEW test_view AS SELECT * FROM test_table";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createTableSql);
                stmt.executeUpdate(createViewSql);
            }
        } catch (

        SQLException e) {
            fail("Failed to create tables and views: " + e.getMessage());
        }
    }

    @AfterAll
    public static void tearDown() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String dropTableSql = "DROP TABLE test_table";
            String dropViewSql = "DROP VIEW test_view";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(dropTableSql);
                stmt.executeUpdate(dropViewSql);
            } catch (SQLException e) {
                fail("Failed to drop tables and views: " + e.getMessage());
            }
        } catch (SQLException e) {
            fail("Failed to establish connection to the database: " + e.getMessage());
        }
    }

    @Test
    public void testConnection() {
        // Test the database connection by attempting to connect to the database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            assertTrue(conn.isValid(5));
        } catch (SQLException e) {
            fail("Failed to establish a database connection: " + e.getMessage());
        }
    }

    @Test
    public void testCreateRecord() {
        // Test the ability to insert a record into the database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO test_table (col1, col2) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, 1);
                pstmt.setString(2, "test");
                int rowsAffected = pstmt.executeUpdate();
                assertEquals(1, rowsAffected);
            }
        } catch (SQLException e) {
            fail("Failed to insert record into the database: " + e.getMessage());
        }
    }

    @Test
    public void testReadRecord() {
        // Test the ability to read a record from the database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT col1, col2 FROM test_table WHERE col1 = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, 1);
                try (ResultSet rs = pstmt.executeQuery()) {
                    assertTrue(rs.next());
                    assertEquals(1, rs.getInt("col1"));
                    assertEquals("test", rs.getString("col2"));
                }
            }
        } catch (SQLException e) {
            fail("Failed to read record from the database: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateRecord() {
        // Test the ability to update a record in the database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE test_table SET col2 = ? WHERE col1 = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "Jane Doe");
                pstmt.setInt(2, 1);
                int rowsUpdated = pstmt.executeUpdate();
                assertEquals(1, rowsUpdated);
            }
        } catch (SQLException e) {
            fail("Failed to update record in the database: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteRecord() {
        // Test the ability to delete a record from the database
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM test_table WHERE col1 = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, 1);
                int rowsDeleted = pstmt.executeUpdate();
                assertEquals(1, rowsDeleted);
            }
        } catch (SQLException e) {
            fail("Failed to delete record from the database: " + e.getMessage());
        }
    }
}