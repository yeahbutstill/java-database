package yeahbutstill.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariCP() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/mister_buah");
        config.setUsername("root");
        config.setPassword("Alone2222`");

        // konfiguration pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        try {
            HikariDataSource dataSource = new HikariDataSource(config);

            Connection connection = dataSource.getConnection();
            System.out.println("Sukses memanggil koneksi");

            // mengembalikan ke pool
            connection.close();
            System.out.println("Sukses mengembalikan koneksi");

            dataSource.close();
            System.out.println("Sukses menutup pool");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }

    }

    @Test
    void testUtil() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();

    }
}
