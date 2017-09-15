package pl.lodz.sda.connector;

import pl.lodz.sda.dao.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcConnector implements JdbcConnectorApi {

    @Override
    public void batchInsert(List<Employee> employees, DB db) {
        Connection connection = getDBConnection(db);
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

    /**
     * int id;
     * Date birth_date;
     * String first_name;
     * String last_name;
     * char gender;
     * Date hire_date;
     *
     * @return List<Employee>
     */
    @Override
    public List<Employee> selectEmployees(DB db) {
        Connection dbConnection = getDBConnection(db);
        String sql = Employee.selectQuery();
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Date birth_date = resultSet.getDate(2);
                String first_name = resultSet.getString(3);
                String last_name = resultSet.getString(4);
                String gender = resultSet.getString(5);
                Date hire_date = resultSet.getDate(6);
                employees.add(new Employee(id, birth_date,
                        first_name, last_name,
                        gender.charAt(0), hire_date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Connection getDBConnection(DB db) {
        DBFactory dbFactory = new DBFactory();
        ConnectionCredentials connectionCredentials = dbFactory.chooseDb(db);
        try {
            return DriverManager.getConnection(
                    connectionCredentials.connection,
                    connectionCredentials.user,
                    connectionCredentials.password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
