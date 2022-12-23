package com.transport.transportapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.aspectj.lang.reflect.CatchClauseSignature;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.refEq;

public class DatabaseConnectionTest {
    // Replace these values with your own database connection details
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String DB_USERNAME = "test";
    private static final String DB_PASSWORD = "secret";

    @Test
    public void testDatabaseConnection() {
        Connection connection = null;
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a DELETE query
            //String deleteQuery = "DELETE FROM users WHERE username = 'bob'";
           // statement.executeUpdate(deleteQuery);
            
            // Execute a CREATE USER query
            //String createUserQuery = "CREATE USER bob IDENTIFIED BY test";
           // statement.executeUpdate(createUserQuery);

            // Execute a GRANT query
            //String grantQuery = "GRANT CONNECT TO bob";
            //statement.executeUpdate(grantQuery);

            // Execute a UPDATE query
            //String updateQuery = "UPDATE users SET password = 'test' WHERE username = 'bob'";
            //statement.executeUpdate(updateQuery);

          

            // Execute a SELECT query
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate over the result set and print the results
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println(username + " " + password);
            }
        } catch (ClassNotFoundException e) {
            fail("Unable to load Oracle JDBC driver");
        } catch (SQLException e) {
            fail("Error establishing database connection: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    fail("Error closing database connection: " + e.getMessage());
                }
            }
        }
    }
}
