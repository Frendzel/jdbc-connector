package pl.lodz.sda;

import java.sql.*;

public class H2MemoryDatabaseExample {

    // DB_CLOSE_DELAY=-1 -> H2 będzie trzymać dane tak długo jak istnieje VM
//    private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost/~/test";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) throws Exception {
        try {
//            Server server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
//            insertWithStatement();
//            insertWithPreparedStatement();
//            server.stop();
            insertTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertTestData() {
        Connection connection = getDBConnection();
        if (connection == null) {
            throw new RuntimeException();
        }

        String createQuery = "CREATE TABLE EMPLOYEE" +
                "(id int primary key, " +
                "first_name varchar(50), " +
                "last_name varchar(50), " +
                "gender char(1), " +
                "age tinyint)";
        String insertQuery = "INSERT INTO EMPLOYEE VALUES(" +
                "1, 'Janusz', 'Kowalski', 'T', 27)";
        String selectQuery = "select * from EMPLOYEE";
        Statement statement;
        try {
            statement = connection.createStatement();
            System.out.println("createQuery: " +
                    statement.execute(createQuery));
            System.out.println("insertQuery: " +
                    statement.execute(insertQuery));
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                System.out.println("id " +
                        resultSet.getInt(1) + " " +
                        " imie: " + resultSet.getString(2) +
                        " nazwisko: " + resultSet.getString(3) +
                        " plec: " + resultSet.getString(4) +
                        " wiek: " + resultSet.getInt(5));
            }
            statement.close();
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

    private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = getDBConnection();

        String CreateQuery = "CREATE TABLE PERSON(id int primary key, name varchar(255))";
        String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";
        String SelectQuery = "select * from PERSON";

        try {
            connection.setAutoCommit(false);
            PreparedStatement createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();

            PreparedStatement insertPreparedStatement = connection.prepareStatement(InsertQuery);
            insertPreparedStatement.setInt(1, 1);
            insertPreparedStatement.setString(2, "Jose");


            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            PreparedStatement selectPreparedStatement = connection.prepareStatement(SelectQuery);
            ResultSet rs = selectPreparedStatement.executeQuery();
            System.out.println("H2 In-Memory Database inserted through PreparedStatement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }
            selectPreparedStatement.close();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static void insertWithStatement() throws SQLException {
        Connection connection = getDBConnection();
        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.execute("CREATE TABLE PERSON(id int primary key, name varchar(255))");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(2, 'Sonia')");
            stmt.execute("INSERT INTO PERSON(id, name) VALUES(3, 'Asha')");

            ResultSet rs = stmt.executeQuery("select * from PERSON");
            System.out.println("H2 In-Memory Database inserted through Statement");
            while (rs.next()) {
                System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            }

            //stmt.execute("DROP TABLE PERSON");
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static Connection getDBConnection() {
        try {
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     *  SavePointy: https://www.tutorialspoint.com/jdbc/jdbc-transactions.htm
     *  Batch: https://www.tutorialspoint.com/jdbc/jdbc-batch-processing.htm
     *  PreparedStatement
     *  Propertasy w mavenie
     *  Logowanie
     *  Podłączenie do mysqla
     * https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html
     *
     * Podsumowanie:
     * https://www.tutorialspoint.com/jdbc/jdbc-quick-guide.htm
     */
}
