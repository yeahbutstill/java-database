package yeahbutstill.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql ="INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int data = 0; data < 1000; data++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "dani@yeahbutstill.com");
            preparedStatement.setString(2, "jancok");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        preparedStatement.close();

        connection.commit();
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql ="INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int data = 0; data < 1000; data++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "dani@yeahbutstill.com");
            preparedStatement.setString(2, "jancok");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        preparedStatement.close();

        connection.rollback();
        connection.close();
    }
}
