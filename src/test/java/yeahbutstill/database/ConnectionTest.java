package yeahbutstill.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    // not for in the case, this is normal code basic on your main
//    static {
//
//        try {
//            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
//            DriverManager.registerDriver(mysqlDriver);
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//
//    }

    // now this is for unit test
    @BeforeAll
    static void beforeAll() {

        // registrasi driver just once
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Test
    void testConnection() {

        String jdbcUrl = "jdbc:mysql://localhost:3306/mister_buah";
        String username = "root";
        String password = "Alone2222`";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Successfully");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }

    }

    @Test
    void testCloseConnection() {

        String jdbcUrl = "jdbc:mysql://localhost:3306/mister_buah";
        String username = "root";
        String password = "Alone2222`";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected Successfully");
            connection.close();
            System.out.println("Close Connected Successfully");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }

    }

}
