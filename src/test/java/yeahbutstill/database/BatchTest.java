package yeahbutstill.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    // jangan ditumpuk jadi 1 juta
    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments (email, comment) VALUES ('blabla@mister_buah.com', 'test hai')";
        for (int i = 0; i < 1_000; i++) {
            // execute batch
            statement.addBatch(sql);
        }

        // langsung tumpuk semuanya seribu dalam satu batch
        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        for (int i = 0; i < 1_000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "dani@yeahbutstill.com");
            preparedStatement.setString(2, "yaelah deh");
            preparedStatement.addBatch();
        }

        // langsung tumpuk semuanya seribu dalam satu batch
        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }

}
