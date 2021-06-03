package yeahbutstill.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncremenTest {

    @Test
    void testAutoIncrement() throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, "azhilla@yeahbutstill.com");
        preparedStatement.setString(2, "I LOVE U");

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            System.out.println("id comment : " + resultSet.getInt(1));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
