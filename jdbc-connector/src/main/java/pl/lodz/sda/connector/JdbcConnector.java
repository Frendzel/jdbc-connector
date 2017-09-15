package pl.lodz.sda.connector;

import pl.lodz.sda.dao.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class JdbcConnector implements JdbcConnectorApi {

    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    @Override
    public void batchInsert(List<Employee> employees) {
        Connection connection = getDBConnection();
        try {
            Statement statement = connection.createStatement();
            for (Employee emp : employees) {
                String sql = emp.toStatementInsertQuery();
                statement.addBatch(sql);
            }
            int[] insertedRows = statement.executeBatch();
            System.out.println("insertedRows: " +
                    Arrays.toString(insertedRows));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getDBConnection() {
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
